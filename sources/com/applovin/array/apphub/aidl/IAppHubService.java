package com.applovin.array.apphub.aidl;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.applovin.array.apphub.aidl.IAppHubDirectDownloadServiceCallback;

/* loaded from: classes.dex */
public interface IAppHubService extends IInterface {
    public static final String DESCRIPTOR = "com.applovin.array.apphub.aidl.IAppHubService";

    /* loaded from: classes.dex */
    public static class Default implements IAppHubService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.applovin.array.apphub.aidl.IAppHubService
        public void directInstall(String str, Bundle bundle, IAppHubDirectDownloadServiceCallback iAppHubDirectDownloadServiceCallback) throws RemoteException {
        }

        @Override // com.applovin.array.apphub.aidl.IAppHubService
        public void dismissDirectDownloadAppDetails(String str) throws RemoteException {
        }

        @Override // com.applovin.array.apphub.aidl.IAppHubService
        public long getAppHubVersionCode() throws RemoteException {
            return 0L;
        }

        @Override // com.applovin.array.apphub.aidl.IAppHubService
        public Bundle getEnabledFeatures() throws RemoteException {
            return null;
        }

        @Override // com.applovin.array.apphub.aidl.IAppHubService
        public String getRandomUserToken() throws RemoteException {
            return null;
        }

        @Override // com.applovin.array.apphub.aidl.IAppHubService
        public void showDirectDownloadAppDetails(String str, IAppHubDirectDownloadServiceCallback iAppHubDirectDownloadServiceCallback) throws RemoteException {
        }

        @Override // com.applovin.array.apphub.aidl.IAppHubService
        public void showDirectDownloadAppDetailsWithExtra(String str, Bundle bundle, IAppHubDirectDownloadServiceCallback iAppHubDirectDownloadServiceCallback) throws RemoteException {
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IAppHubService {
        static final int TRANSACTION_directInstall = 7;
        static final int TRANSACTION_dismissDirectDownloadAppDetails = 5;
        static final int TRANSACTION_getAppHubVersionCode = 2;
        static final int TRANSACTION_getEnabledFeatures = 3;
        static final int TRANSACTION_getRandomUserToken = 1;
        static final int TRANSACTION_showDirectDownloadAppDetails = 4;
        static final int TRANSACTION_showDirectDownloadAppDetailsWithExtra = 6;

        /* loaded from: classes.dex */
        public static class Proxy implements IAppHubService {
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.applovin.array.apphub.aidl.IAppHubService
            public void directInstall(String str, Bundle bundle, IAppHubDirectDownloadServiceCallback iAppHubDirectDownloadServiceCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IAppHubService.DESCRIPTOR);
                    obtain.writeString(str);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    obtain.writeStrongInterface(iAppHubDirectDownloadServiceCallback);
                    this.mRemote.transact(7, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.applovin.array.apphub.aidl.IAppHubService
            public void dismissDirectDownloadAppDetails(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IAppHubService.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.applovin.array.apphub.aidl.IAppHubService
            public long getAppHubVersionCode() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IAppHubService.DESCRIPTOR);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readLong();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.applovin.array.apphub.aidl.IAppHubService
            public Bundle getEnabledFeatures() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IAppHubService.DESCRIPTOR);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return (Bundle) _Parcel.readTypedObject(obtain2, Bundle.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return IAppHubService.DESCRIPTOR;
            }

            @Override // com.applovin.array.apphub.aidl.IAppHubService
            public String getRandomUserToken() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IAppHubService.DESCRIPTOR);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.applovin.array.apphub.aidl.IAppHubService
            public void showDirectDownloadAppDetails(String str, IAppHubDirectDownloadServiceCallback iAppHubDirectDownloadServiceCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IAppHubService.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongInterface(iAppHubDirectDownloadServiceCallback);
                    this.mRemote.transact(4, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.applovin.array.apphub.aidl.IAppHubService
            public void showDirectDownloadAppDetailsWithExtra(String str, Bundle bundle, IAppHubDirectDownloadServiceCallback iAppHubDirectDownloadServiceCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IAppHubService.DESCRIPTOR);
                    obtain.writeString(str);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    obtain.writeStrongInterface(iAppHubDirectDownloadServiceCallback);
                    this.mRemote.transact(6, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, IAppHubService.DESCRIPTOR);
        }

        public static IAppHubService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IAppHubService.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IAppHubService)) {
                return (IAppHubService) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i9, Parcel parcel, Parcel parcel2, int i10) throws RemoteException {
            if (i9 >= 1 && i9 <= 16777215) {
                parcel.enforceInterface(IAppHubService.DESCRIPTOR);
            }
            if (i9 != 1598968902) {
                switch (i9) {
                    case 1:
                        String randomUserToken = getRandomUserToken();
                        parcel2.writeNoException();
                        parcel2.writeString(randomUserToken);
                        return true;
                    case 2:
                        long appHubVersionCode = getAppHubVersionCode();
                        parcel2.writeNoException();
                        parcel2.writeLong(appHubVersionCode);
                        return true;
                    case 3:
                        Bundle enabledFeatures = getEnabledFeatures();
                        parcel2.writeNoException();
                        _Parcel.writeTypedObject(parcel2, enabledFeatures, 1);
                        return true;
                    case 4:
                        showDirectDownloadAppDetails(parcel.readString(), IAppHubDirectDownloadServiceCallback.Stub.asInterface(parcel.readStrongBinder()));
                        return true;
                    case 5:
                        dismissDirectDownloadAppDetails(parcel.readString());
                        parcel2.writeNoException();
                        return true;
                    case 6:
                        showDirectDownloadAppDetailsWithExtra(parcel.readString(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR), IAppHubDirectDownloadServiceCallback.Stub.asInterface(parcel.readStrongBinder()));
                        return true;
                    case 7:
                        directInstall(parcel.readString(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR), IAppHubDirectDownloadServiceCallback.Stub.asInterface(parcel.readStrongBinder()));
                        return true;
                    default:
                        return super.onTransact(i9, parcel, parcel2, i10);
                }
            }
            parcel2.writeString(IAppHubService.DESCRIPTOR);
            return true;
        }
    }

    /* loaded from: classes.dex */
    public static class _Parcel {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T readTypedObject(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void writeTypedObject(Parcel parcel, T t9, int i9) {
            if (t9 != null) {
                parcel.writeInt(1);
                t9.writeToParcel(parcel, i9);
            } else {
                parcel.writeInt(0);
            }
        }
    }

    void directInstall(String str, Bundle bundle, IAppHubDirectDownloadServiceCallback iAppHubDirectDownloadServiceCallback) throws RemoteException;

    void dismissDirectDownloadAppDetails(String str) throws RemoteException;

    long getAppHubVersionCode() throws RemoteException;

    Bundle getEnabledFeatures() throws RemoteException;

    String getRandomUserToken() throws RemoteException;

    void showDirectDownloadAppDetails(String str, IAppHubDirectDownloadServiceCallback iAppHubDirectDownloadServiceCallback) throws RemoteException;

    void showDirectDownloadAppDetailsWithExtra(String str, Bundle bundle, IAppHubDirectDownloadServiceCallback iAppHubDirectDownloadServiceCallback) throws RemoteException;
}
