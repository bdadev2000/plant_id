package com.mbridge.msdk.foundation.same.e;

/* loaded from: classes3.dex */
public abstract class a implements Runnable {
    public static long id;
    public InterfaceC0160a mListener;
    public b mState = b.READY;

    /* renamed from: com.mbridge.msdk.foundation.same.e.a$a, reason: collision with other inner class name */
    /* loaded from: classes3.dex */
    public interface InterfaceC0160a {
        void a(b bVar);
    }

    /* loaded from: classes3.dex */
    public enum b {
        READY,
        RUNNING,
        PAUSE,
        CANCEL,
        FINISH
    }

    public a() {
        id++;
    }

    private void setState(b bVar) {
        this.mState = bVar;
        InterfaceC0160a interfaceC0160a = this.mListener;
        if (interfaceC0160a != null) {
            interfaceC0160a.a(bVar);
        }
    }

    public final void cancel() {
        b bVar = this.mState;
        b bVar2 = b.CANCEL;
        if (bVar != bVar2) {
            setState(bVar2);
            cancelTask();
        }
    }

    public abstract void cancelTask();

    public final long getId() {
        return id;
    }

    public b getState() {
        return this.mState;
    }

    public abstract void pauseTask(boolean z8);

    @Override // java.lang.Runnable
    public final void run() {
        try {
            if (this.mState == b.READY) {
                setState(b.RUNNING);
                runTask();
                setState(b.FINISH);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public abstract void runTask();

    public void setOnStateChangeListener(InterfaceC0160a interfaceC0160a) {
        this.mListener = interfaceC0160a;
    }

    public final void setPause(boolean z8) {
        b bVar = this.mState;
        b bVar2 = b.PAUSE;
        if (bVar != bVar2 && bVar != b.CANCEL && bVar != b.FINISH) {
            if (z8) {
                setState(bVar2);
            } else {
                setState(b.RUNNING);
            }
            pauseTask(z8);
        }
    }
}
