package com.mbridge.msdk.thrid.okhttp.internal.cache;

import com.mbridge.msdk.thrid.okhttp.internal.Util;
import com.mbridge.msdk.thrid.okhttp.internal.io.FileSystem;
import com.mbridge.msdk.thrid.okhttp.internal.platform.Platform;
import com.mbridge.msdk.thrid.okio.BufferedSink;
import com.mbridge.msdk.thrid.okio.BufferedSource;
import com.mbridge.msdk.thrid.okio.Okio;
import com.mbridge.msdk.thrid.okio.Sink;
import com.mbridge.msdk.thrid.okio.Source;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Flushable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.NoSuchElementException;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import x0.AbstractC2914a;

/* loaded from: classes3.dex */
public final class DiskLruCache implements Closeable, Flushable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final long ANY_SEQUENCE_NUMBER = -1;
    private static final String CLEAN = "CLEAN";
    private static final String DIRTY = "DIRTY";
    static final String JOURNAL_FILE = "journal";
    static final String JOURNAL_FILE_BACKUP = "journal.bkp";
    static final String JOURNAL_FILE_TEMP = "journal.tmp";
    static final Pattern LEGAL_KEY_PATTERN = Pattern.compile("[a-z0-9_-]{1,120}");
    static final String MAGIC = "libcore.io.DiskLruCache";
    private static final String READ = "READ";
    private static final String REMOVE = "REMOVE";
    static final String VERSION_1 = "1";
    private final int appVersion;
    boolean closed;
    final File directory;
    private final Executor executor;
    final FileSystem fileSystem;
    boolean hasJournalErrors;
    boolean initialized;
    private final File journalFile;
    private final File journalFileBackup;
    private final File journalFileTmp;
    BufferedSink journalWriter;
    private long maxSize;
    boolean mostRecentRebuildFailed;
    boolean mostRecentTrimFailed;
    int redundantOpCount;
    final int valueCount;
    private long size = 0;
    final LinkedHashMap<String, Entry> lruEntries = new LinkedHashMap<>(0, 0.75f, true);
    private long nextSequenceNumber = 0;
    private final Runnable cleanupRunnable = new Runnable() { // from class: com.mbridge.msdk.thrid.okhttp.internal.cache.DiskLruCache.1
        @Override // java.lang.Runnable
        public void run() {
            synchronized (DiskLruCache.this) {
                DiskLruCache diskLruCache = DiskLruCache.this;
                if ((!diskLruCache.initialized) | diskLruCache.closed) {
                    return;
                }
                try {
                    diskLruCache.trimToSize();
                } catch (IOException unused) {
                    DiskLruCache.this.mostRecentTrimFailed = true;
                }
                try {
                    if (DiskLruCache.this.journalRebuildRequired()) {
                        DiskLruCache.this.rebuildJournal();
                        DiskLruCache.this.redundantOpCount = 0;
                    }
                } catch (IOException unused2) {
                    DiskLruCache diskLruCache2 = DiskLruCache.this;
                    diskLruCache2.mostRecentRebuildFailed = true;
                    diskLruCache2.journalWriter = Okio.buffer(Okio.blackhole());
                }
            }
        }
    };

    /* loaded from: classes3.dex */
    public final class Editor {
        private boolean done;
        final Entry entry;
        final boolean[] written;

        public Editor(Entry entry) {
            boolean[] zArr;
            this.entry = entry;
            if (entry.readable) {
                zArr = null;
            } else {
                zArr = new boolean[DiskLruCache.this.valueCount];
            }
            this.written = zArr;
        }

        public void abort() throws IOException {
            synchronized (DiskLruCache.this) {
                try {
                    if (!this.done) {
                        if (this.entry.currentEditor == this) {
                            DiskLruCache.this.completeEdit(this, false);
                        }
                        this.done = true;
                    } else {
                        throw new IllegalStateException();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public void abortUnlessCommitted() {
            synchronized (DiskLruCache.this) {
                if (!this.done && this.entry.currentEditor == this) {
                    try {
                        DiskLruCache.this.completeEdit(this, false);
                    } catch (IOException unused) {
                    }
                }
            }
        }

        public void commit() throws IOException {
            synchronized (DiskLruCache.this) {
                try {
                    if (!this.done) {
                        if (this.entry.currentEditor == this) {
                            DiskLruCache.this.completeEdit(this, true);
                        }
                        this.done = true;
                    } else {
                        throw new IllegalStateException();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public void detach() {
            if (this.entry.currentEditor == this) {
                int i9 = 0;
                while (true) {
                    DiskLruCache diskLruCache = DiskLruCache.this;
                    if (i9 < diskLruCache.valueCount) {
                        try {
                            diskLruCache.fileSystem.delete(this.entry.dirtyFiles[i9]);
                        } catch (IOException unused) {
                        }
                        i9++;
                    } else {
                        this.entry.currentEditor = null;
                        return;
                    }
                }
            }
        }

        public Sink newSink(int i9) {
            synchronized (DiskLruCache.this) {
                try {
                    if (!this.done) {
                        Entry entry = this.entry;
                        if (entry.currentEditor != this) {
                            return Okio.blackhole();
                        }
                        if (!entry.readable) {
                            this.written[i9] = true;
                        }
                        try {
                            return new FaultHidingSink(DiskLruCache.this.fileSystem.sink(entry.dirtyFiles[i9])) { // from class: com.mbridge.msdk.thrid.okhttp.internal.cache.DiskLruCache.Editor.1
                                @Override // com.mbridge.msdk.thrid.okhttp.internal.cache.FaultHidingSink
                                public void onException(IOException iOException) {
                                    synchronized (DiskLruCache.this) {
                                        Editor.this.detach();
                                    }
                                }
                            };
                        } catch (FileNotFoundException unused) {
                            return Okio.blackhole();
                        }
                    }
                    throw new IllegalStateException();
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public Source newSource(int i9) {
            synchronized (DiskLruCache.this) {
                try {
                    if (!this.done) {
                        Entry entry = this.entry;
                        if (!entry.readable || entry.currentEditor != this) {
                            return null;
                        }
                        try {
                            return DiskLruCache.this.fileSystem.source(entry.cleanFiles[i9]);
                        } catch (FileNotFoundException unused) {
                            return null;
                        }
                    }
                    throw new IllegalStateException();
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    /* loaded from: classes3.dex */
    public final class Entry {
        final File[] cleanFiles;
        Editor currentEditor;
        final File[] dirtyFiles;
        final String key;
        final long[] lengths;
        boolean readable;
        long sequenceNumber;

        public Entry(String str) {
            this.key = str;
            int i9 = DiskLruCache.this.valueCount;
            this.lengths = new long[i9];
            this.cleanFiles = new File[i9];
            this.dirtyFiles = new File[i9];
            StringBuilder sb = new StringBuilder(str);
            sb.append('.');
            int length = sb.length();
            for (int i10 = 0; i10 < DiskLruCache.this.valueCount; i10++) {
                sb.append(i10);
                this.cleanFiles[i10] = new File(DiskLruCache.this.directory, sb.toString());
                sb.append(".tmp");
                this.dirtyFiles[i10] = new File(DiskLruCache.this.directory, sb.toString());
                sb.setLength(length);
            }
        }

        private IOException invalidLengths(String[] strArr) throws IOException {
            throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
        }

        public void setLengths(String[] strArr) throws IOException {
            if (strArr.length == DiskLruCache.this.valueCount) {
                for (int i9 = 0; i9 < strArr.length; i9++) {
                    try {
                        this.lengths[i9] = Long.parseLong(strArr[i9]);
                    } catch (NumberFormatException unused) {
                        throw invalidLengths(strArr);
                    }
                }
                return;
            }
            throw invalidLengths(strArr);
        }

        public Snapshot snapshot() {
            Source source;
            if (Thread.holdsLock(DiskLruCache.this)) {
                Source[] sourceArr = new Source[DiskLruCache.this.valueCount];
                long[] jArr = (long[]) this.lengths.clone();
                int i9 = 0;
                int i10 = 0;
                while (true) {
                    try {
                        DiskLruCache diskLruCache = DiskLruCache.this;
                        if (i10 < diskLruCache.valueCount) {
                            sourceArr[i10] = diskLruCache.fileSystem.source(this.cleanFiles[i10]);
                            i10++;
                        } else {
                            return new Snapshot(this.key, this.sequenceNumber, sourceArr, jArr);
                        }
                    } catch (FileNotFoundException unused) {
                        while (true) {
                            DiskLruCache diskLruCache2 = DiskLruCache.this;
                            if (i9 < diskLruCache2.valueCount && (source = sourceArr[i9]) != null) {
                                Util.closeQuietly(source);
                                i9++;
                            } else {
                                try {
                                    diskLruCache2.removeEntry(this);
                                    return null;
                                } catch (IOException unused2) {
                                    return null;
                                }
                            }
                        }
                    }
                }
            } else {
                throw new AssertionError();
            }
        }

        public void writeLengths(BufferedSink bufferedSink) throws IOException {
            for (long j7 : this.lengths) {
                bufferedSink.writeByte(32).writeDecimalLong(j7);
            }
        }
    }

    /* loaded from: classes3.dex */
    public final class Snapshot implements Closeable {
        private final String key;
        private final long[] lengths;
        private final long sequenceNumber;
        private final Source[] sources;

        public Snapshot(String str, long j7, Source[] sourceArr, long[] jArr) {
            this.key = str;
            this.sequenceNumber = j7;
            this.sources = sourceArr;
            this.lengths = jArr;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            for (Source source : this.sources) {
                Util.closeQuietly(source);
            }
        }

        public Editor edit() throws IOException {
            return DiskLruCache.this.edit(this.key, this.sequenceNumber);
        }

        public long getLength(int i9) {
            return this.lengths[i9];
        }

        public Source getSource(int i9) {
            return this.sources[i9];
        }

        public String key() {
            return this.key;
        }
    }

    public DiskLruCache(FileSystem fileSystem, File file, int i9, int i10, long j7, Executor executor) {
        this.fileSystem = fileSystem;
        this.directory = file;
        this.appVersion = i9;
        this.journalFile = new File(file, JOURNAL_FILE);
        this.journalFileTmp = new File(file, JOURNAL_FILE_TEMP);
        this.journalFileBackup = new File(file, JOURNAL_FILE_BACKUP);
        this.valueCount = i10;
        this.maxSize = j7;
        this.executor = executor;
    }

    private synchronized void checkNotClosed() {
        if (isClosed()) {
            throw new IllegalStateException("cache is closed");
        }
    }

    public static DiskLruCache create(FileSystem fileSystem, File file, int i9, int i10, long j7) {
        if (j7 > 0) {
            if (i10 > 0) {
                return new DiskLruCache(fileSystem, file, i9, i10, j7, new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), Util.threadFactory("OkHttp DiskLruCache", true)));
            }
            throw new IllegalArgumentException("valueCount <= 0");
        }
        throw new IllegalArgumentException("maxSize <= 0");
    }

    private BufferedSink newJournalWriter() throws FileNotFoundException {
        return Okio.buffer(new FaultHidingSink(this.fileSystem.appendingSink(this.journalFile)) { // from class: com.mbridge.msdk.thrid.okhttp.internal.cache.DiskLruCache.2
            static final /* synthetic */ boolean $assertionsDisabled = false;

            @Override // com.mbridge.msdk.thrid.okhttp.internal.cache.FaultHidingSink
            public void onException(IOException iOException) {
                DiskLruCache.this.hasJournalErrors = true;
            }
        });
    }

    private void processJournal() throws IOException {
        this.fileSystem.delete(this.journalFileTmp);
        Iterator<Entry> it = this.lruEntries.values().iterator();
        while (it.hasNext()) {
            Entry next = it.next();
            int i9 = 0;
            if (next.currentEditor == null) {
                while (i9 < this.valueCount) {
                    this.size += next.lengths[i9];
                    i9++;
                }
            } else {
                next.currentEditor = null;
                while (i9 < this.valueCount) {
                    this.fileSystem.delete(next.cleanFiles[i9]);
                    this.fileSystem.delete(next.dirtyFiles[i9]);
                    i9++;
                }
                it.remove();
            }
        }
    }

    private void readJournal() throws IOException {
        BufferedSource buffer = Okio.buffer(this.fileSystem.source(this.journalFile));
        try {
            String readUtf8LineStrict = buffer.readUtf8LineStrict();
            String readUtf8LineStrict2 = buffer.readUtf8LineStrict();
            String readUtf8LineStrict3 = buffer.readUtf8LineStrict();
            String readUtf8LineStrict4 = buffer.readUtf8LineStrict();
            String readUtf8LineStrict5 = buffer.readUtf8LineStrict();
            if (MAGIC.equals(readUtf8LineStrict) && "1".equals(readUtf8LineStrict2) && Integer.toString(this.appVersion).equals(readUtf8LineStrict3) && Integer.toString(this.valueCount).equals(readUtf8LineStrict4) && "".equals(readUtf8LineStrict5)) {
                int i9 = 0;
                while (true) {
                    try {
                        readJournalLine(buffer.readUtf8LineStrict());
                        i9++;
                    } catch (EOFException unused) {
                        this.redundantOpCount = i9 - this.lruEntries.size();
                        if (!buffer.exhausted()) {
                            rebuildJournal();
                        } else {
                            this.journalWriter = newJournalWriter();
                        }
                        Util.closeQuietly(buffer);
                        return;
                    }
                }
            } else {
                throw new IOException("unexpected journal header: [" + readUtf8LineStrict + ", " + readUtf8LineStrict2 + ", " + readUtf8LineStrict4 + ", " + readUtf8LineStrict5 + "]");
            }
        } catch (Throwable th) {
            Util.closeQuietly(buffer);
            throw th;
        }
    }

    private void readJournalLine(String str) throws IOException {
        String substring;
        int indexOf = str.indexOf(32);
        if (indexOf != -1) {
            int i9 = indexOf + 1;
            int indexOf2 = str.indexOf(32, i9);
            if (indexOf2 == -1) {
                substring = str.substring(i9);
                if (indexOf == 6 && str.startsWith(REMOVE)) {
                    this.lruEntries.remove(substring);
                    return;
                }
            } else {
                substring = str.substring(i9, indexOf2);
            }
            Entry entry = this.lruEntries.get(substring);
            if (entry == null) {
                entry = new Entry(substring);
                this.lruEntries.put(substring, entry);
            }
            if (indexOf2 != -1 && indexOf == 5 && str.startsWith(CLEAN)) {
                String[] split = str.substring(indexOf2 + 1).split(" ");
                entry.readable = true;
                entry.currentEditor = null;
                entry.setLengths(split);
                return;
            }
            if (indexOf2 == -1 && indexOf == 5 && str.startsWith(DIRTY)) {
                entry.currentEditor = new Editor(entry);
                return;
            } else if (indexOf2 == -1 && indexOf == 4 && str.startsWith(READ)) {
                return;
            } else {
                throw new IOException("unexpected journal line: ".concat(str));
            }
        }
        throw new IOException("unexpected journal line: ".concat(str));
    }

    private void validateKey(String str) {
        if (LEGAL_KEY_PATTERN.matcher(str).matches()) {
        } else {
            throw new IllegalArgumentException(AbstractC2914a.e("keys must match regex [a-z0-9_-]{1,120}: \"", str, "\""));
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        try {
            if (this.initialized && !this.closed) {
                for (Entry entry : (Entry[]) this.lruEntries.values().toArray(new Entry[this.lruEntries.size()])) {
                    Editor editor = entry.currentEditor;
                    if (editor != null) {
                        editor.abort();
                    }
                }
                trimToSize();
                this.journalWriter.close();
                this.journalWriter = null;
                this.closed = true;
                return;
            }
            this.closed = true;
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized void completeEdit(Editor editor, boolean z8) throws IOException {
        Entry entry = editor.entry;
        if (entry.currentEditor == editor) {
            if (z8 && !entry.readable) {
                for (int i9 = 0; i9 < this.valueCount; i9++) {
                    if (editor.written[i9]) {
                        if (!this.fileSystem.exists(entry.dirtyFiles[i9])) {
                            editor.abort();
                            return;
                        }
                    } else {
                        editor.abort();
                        throw new IllegalStateException("Newly created entry didn't create value for index " + i9);
                    }
                }
            }
            for (int i10 = 0; i10 < this.valueCount; i10++) {
                File file = entry.dirtyFiles[i10];
                if (z8) {
                    if (this.fileSystem.exists(file)) {
                        File file2 = entry.cleanFiles[i10];
                        this.fileSystem.rename(file, file2);
                        long j7 = entry.lengths[i10];
                        long size = this.fileSystem.size(file2);
                        entry.lengths[i10] = size;
                        this.size = (this.size - j7) + size;
                    }
                } else {
                    this.fileSystem.delete(file);
                }
            }
            this.redundantOpCount++;
            entry.currentEditor = null;
            if (entry.readable | z8) {
                entry.readable = true;
                this.journalWriter.writeUtf8(CLEAN).writeByte(32);
                this.journalWriter.writeUtf8(entry.key);
                entry.writeLengths(this.journalWriter);
                this.journalWriter.writeByte(10);
                if (z8) {
                    long j9 = this.nextSequenceNumber;
                    this.nextSequenceNumber = 1 + j9;
                    entry.sequenceNumber = j9;
                }
            } else {
                this.lruEntries.remove(entry.key);
                this.journalWriter.writeUtf8(REMOVE).writeByte(32);
                this.journalWriter.writeUtf8(entry.key);
                this.journalWriter.writeByte(10);
            }
            this.journalWriter.flush();
            if (this.size > this.maxSize || journalRebuildRequired()) {
                this.executor.execute(this.cleanupRunnable);
            }
            return;
        }
        throw new IllegalStateException();
    }

    public void delete() throws IOException {
        close();
        this.fileSystem.deleteContents(this.directory);
    }

    public Editor edit(String str) throws IOException {
        return edit(str, -1L);
    }

    public synchronized void evictAll() throws IOException {
        try {
            initialize();
            for (Entry entry : (Entry[]) this.lruEntries.values().toArray(new Entry[this.lruEntries.size()])) {
                removeEntry(entry);
            }
            this.mostRecentTrimFailed = false;
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // java.io.Flushable
    public synchronized void flush() throws IOException {
        if (!this.initialized) {
            return;
        }
        checkNotClosed();
        trimToSize();
        this.journalWriter.flush();
    }

    public synchronized Snapshot get(String str) throws IOException {
        initialize();
        checkNotClosed();
        validateKey(str);
        Entry entry = this.lruEntries.get(str);
        if (entry != null && entry.readable) {
            Snapshot snapshot = entry.snapshot();
            if (snapshot == null) {
                return null;
            }
            this.redundantOpCount++;
            this.journalWriter.writeUtf8(READ).writeByte(32).writeUtf8(str).writeByte(10);
            if (journalRebuildRequired()) {
                this.executor.execute(this.cleanupRunnable);
            }
            return snapshot;
        }
        return null;
    }

    public File getDirectory() {
        return this.directory;
    }

    public synchronized long getMaxSize() {
        return this.maxSize;
    }

    public synchronized void initialize() throws IOException {
        try {
            if (this.initialized) {
                return;
            }
            if (this.fileSystem.exists(this.journalFileBackup)) {
                if (this.fileSystem.exists(this.journalFile)) {
                    this.fileSystem.delete(this.journalFileBackup);
                } else {
                    this.fileSystem.rename(this.journalFileBackup, this.journalFile);
                }
            }
            if (this.fileSystem.exists(this.journalFile)) {
                try {
                    readJournal();
                    processJournal();
                    this.initialized = true;
                    return;
                } catch (IOException e4) {
                    Platform.get().log(5, "DiskLruCache " + this.directory + " is corrupt: " + e4.getMessage() + ", removing", e4);
                    try {
                        delete();
                        this.closed = false;
                    } catch (Throwable th) {
                        this.closed = false;
                        throw th;
                    }
                }
            }
            rebuildJournal();
            this.initialized = true;
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public synchronized boolean isClosed() {
        return this.closed;
    }

    public boolean journalRebuildRequired() {
        int i9 = this.redundantOpCount;
        if (i9 >= 2000 && i9 >= this.lruEntries.size()) {
            return true;
        }
        return false;
    }

    public synchronized void rebuildJournal() throws IOException {
        try {
            BufferedSink bufferedSink = this.journalWriter;
            if (bufferedSink != null) {
                bufferedSink.close();
            }
            BufferedSink buffer = Okio.buffer(this.fileSystem.sink(this.journalFileTmp));
            try {
                buffer.writeUtf8(MAGIC).writeByte(10);
                buffer.writeUtf8("1").writeByte(10);
                buffer.writeDecimalLong(this.appVersion).writeByte(10);
                buffer.writeDecimalLong(this.valueCount).writeByte(10);
                buffer.writeByte(10);
                for (Entry entry : this.lruEntries.values()) {
                    if (entry.currentEditor != null) {
                        buffer.writeUtf8(DIRTY).writeByte(32);
                        buffer.writeUtf8(entry.key);
                        buffer.writeByte(10);
                    } else {
                        buffer.writeUtf8(CLEAN).writeByte(32);
                        buffer.writeUtf8(entry.key);
                        entry.writeLengths(buffer);
                        buffer.writeByte(10);
                    }
                }
                buffer.close();
                if (this.fileSystem.exists(this.journalFile)) {
                    this.fileSystem.rename(this.journalFile, this.journalFileBackup);
                }
                this.fileSystem.rename(this.journalFileTmp, this.journalFile);
                this.fileSystem.delete(this.journalFileBackup);
                this.journalWriter = newJournalWriter();
                this.hasJournalErrors = false;
                this.mostRecentRebuildFailed = false;
            } catch (Throwable th) {
                buffer.close();
                throw th;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public synchronized boolean remove(String str) throws IOException {
        initialize();
        checkNotClosed();
        validateKey(str);
        Entry entry = this.lruEntries.get(str);
        if (entry == null) {
            return false;
        }
        boolean removeEntry = removeEntry(entry);
        if (removeEntry && this.size <= this.maxSize) {
            this.mostRecentTrimFailed = false;
        }
        return removeEntry;
    }

    public boolean removeEntry(Entry entry) throws IOException {
        Editor editor = entry.currentEditor;
        if (editor != null) {
            editor.detach();
        }
        for (int i9 = 0; i9 < this.valueCount; i9++) {
            this.fileSystem.delete(entry.cleanFiles[i9]);
            long j7 = this.size;
            long[] jArr = entry.lengths;
            this.size = j7 - jArr[i9];
            jArr[i9] = 0;
        }
        this.redundantOpCount++;
        this.journalWriter.writeUtf8(REMOVE).writeByte(32).writeUtf8(entry.key).writeByte(10);
        this.lruEntries.remove(entry.key);
        if (journalRebuildRequired()) {
            this.executor.execute(this.cleanupRunnable);
        }
        return true;
    }

    public synchronized void setMaxSize(long j7) {
        this.maxSize = j7;
        if (this.initialized) {
            this.executor.execute(this.cleanupRunnable);
        }
    }

    public synchronized long size() throws IOException {
        initialize();
        return this.size;
    }

    public synchronized Iterator<Snapshot> snapshots() throws IOException {
        initialize();
        return new Iterator<Snapshot>() { // from class: com.mbridge.msdk.thrid.okhttp.internal.cache.DiskLruCache.3
            final Iterator<Entry> delegate;
            Snapshot nextSnapshot;
            Snapshot removeSnapshot;

            {
                this.delegate = new ArrayList(DiskLruCache.this.lruEntries.values()).iterator();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                Snapshot snapshot;
                if (this.nextSnapshot != null) {
                    return true;
                }
                synchronized (DiskLruCache.this) {
                    try {
                        if (DiskLruCache.this.closed) {
                            return false;
                        }
                        while (this.delegate.hasNext()) {
                            Entry next = this.delegate.next();
                            if (next.readable && (snapshot = next.snapshot()) != null) {
                                this.nextSnapshot = snapshot;
                                return true;
                            }
                        }
                        return false;
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }

            @Override // java.util.Iterator
            public void remove() {
                Snapshot snapshot = this.removeSnapshot;
                if (snapshot != null) {
                    try {
                        DiskLruCache.this.remove(snapshot.key);
                    } catch (IOException unused) {
                    } catch (Throwable th) {
                        this.removeSnapshot = null;
                        throw th;
                    }
                    this.removeSnapshot = null;
                    return;
                }
                throw new IllegalStateException("remove() before next()");
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.Iterator
            public Snapshot next() {
                if (hasNext()) {
                    Snapshot snapshot = this.nextSnapshot;
                    this.removeSnapshot = snapshot;
                    this.nextSnapshot = null;
                    return snapshot;
                }
                throw new NoSuchElementException();
            }
        };
    }

    public void trimToSize() throws IOException {
        while (this.size > this.maxSize) {
            removeEntry(this.lruEntries.values().iterator().next());
        }
        this.mostRecentTrimFailed = false;
    }

    public synchronized Editor edit(String str, long j7) throws IOException {
        initialize();
        checkNotClosed();
        validateKey(str);
        Entry entry = this.lruEntries.get(str);
        if (j7 != -1 && (entry == null || entry.sequenceNumber != j7)) {
            return null;
        }
        if (entry != null && entry.currentEditor != null) {
            return null;
        }
        if (!this.mostRecentTrimFailed && !this.mostRecentRebuildFailed) {
            this.journalWriter.writeUtf8(DIRTY).writeByte(32).writeUtf8(str).writeByte(10);
            this.journalWriter.flush();
            if (this.hasJournalErrors) {
                return null;
            }
            if (entry == null) {
                entry = new Entry(str);
                this.lruEntries.put(str, entry);
            }
            Editor editor = new Editor(entry);
            entry.currentEditor = editor;
            return editor;
        }
        this.executor.execute(this.cleanupRunnable);
        return null;
    }
}
