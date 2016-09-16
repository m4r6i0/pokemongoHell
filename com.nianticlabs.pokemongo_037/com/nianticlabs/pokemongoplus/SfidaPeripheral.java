package com.nianticlabs.pokemongoplus;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.widget.ExploreByTouchHelper;
import android.util.Log;
import com.google.android.gms.location.places.Place;
import com.nianticlabs.pokemongoplus.ble.Peripheral;
import com.nianticlabs.pokemongoplus.ble.Service;
import com.nianticlabs.pokemongoplus.ble.SfidaConstant.BluetoothError;
import com.nianticlabs.pokemongoplus.ble.SfidaConstant.PeripheralState;
import com.nianticlabs.pokemongoplus.ble.callback.CompletionCallback;
import com.nianticlabs.pokemongoplus.ble.callback.ConnectCallback;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import spacemadness.com.lunarconsole.C1562R;

public class SfidaPeripheral extends Peripheral {
    private static final String TAG;
    private BluetoothAdapter bluetoothAdapter;
    private final BluetoothDevice bluetoothDevice;
    private final BluetoothGattCallback bluetoothGattCallback;
    private final BroadcastReceiver bluetoothReceiver;
    private int bondState;
    private ConnectCallback connectCallback;
    private final Context context;
    private ConnectCallback disconnectCallback;
    private CompletionCallback discoverServicesCallback;
    private BluetoothGatt gatt;
    private long nativeHandle;
    private byte[] scanRecord;
    private final HandlerExecutor serialExecutor;
    private final ArrayList<SfidaService> serviceRef;
    private PeripheralState state;

    /* renamed from: com.nianticlabs.pokemongoplus.SfidaPeripheral.1 */
    class C08091 extends BluetoothGattCallback {

        /* renamed from: com.nianticlabs.pokemongoplus.SfidaPeripheral.1.1 */
        class C08031 implements Runnable {
            final /* synthetic */ BluetoothGatt val$gatt;
            final /* synthetic */ int val$newState;
            final /* synthetic */ int val$status;

            C08031(BluetoothGatt bluetoothGatt, int i, int i2) {
                this.val$gatt = bluetoothGatt;
                this.val$status = i;
                this.val$newState = i2;
            }

            public void run() {
                Log.d(SfidaPeripheral.TAG, "onConnectionStateChange");
                SfidaPeripheral.this.onConnectionStateChange(this.val$gatt, this.val$status, this.val$newState);
            }
        }

        /* renamed from: com.nianticlabs.pokemongoplus.SfidaPeripheral.1.2 */
        class C08042 implements Runnable {
            final /* synthetic */ BluetoothGatt val$gatt;
            final /* synthetic */ int val$status;

            C08042(BluetoothGatt bluetoothGatt, int i) {
                this.val$gatt = bluetoothGatt;
                this.val$status = i;
            }

            public void run() {
                Log.d(SfidaPeripheral.TAG, "onServicesDiscovered");
                SfidaPeripheral.this.onServicesDiscovered(this.val$gatt, this.val$status);
            }
        }

        /* renamed from: com.nianticlabs.pokemongoplus.SfidaPeripheral.1.3 */
        class C08053 implements Runnable {
            final /* synthetic */ BluetoothGattCharacteristic val$characteristic;
            final /* synthetic */ BluetoothGatt val$gatt;
            final /* synthetic */ int val$status;

            C08053(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
                this.val$gatt = bluetoothGatt;
                this.val$characteristic = bluetoothGattCharacteristic;
                this.val$status = i;
            }

            public void run() {
                Log.d(SfidaPeripheral.TAG, "onCharacteristicRead");
                synchronized (SfidaPeripheral.this.serviceRef) {
                    Iterator it = SfidaPeripheral.this.serviceRef.iterator();
                    while (it.hasNext()) {
                        ((SfidaService) it.next()).onCharacteristicRead(this.val$gatt, this.val$characteristic, this.val$status);
                    }
                }
            }
        }

        /* renamed from: com.nianticlabs.pokemongoplus.SfidaPeripheral.1.4 */
        class C08064 implements Runnable {
            final /* synthetic */ BluetoothGattCharacteristic val$characteristic;
            final /* synthetic */ BluetoothGatt val$gatt;
            final /* synthetic */ int val$status;

            C08064(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
                this.val$gatt = bluetoothGatt;
                this.val$characteristic = bluetoothGattCharacteristic;
                this.val$status = i;
            }

            public void run() {
                Log.d(SfidaPeripheral.TAG, "onCharacteristicWrite");
                synchronized (SfidaPeripheral.this.serviceRef) {
                    Iterator it = SfidaPeripheral.this.serviceRef.iterator();
                    while (it.hasNext()) {
                        ((SfidaService) it.next()).onCharacteristicWrite(this.val$gatt, this.val$characteristic, this.val$status);
                    }
                }
            }
        }

        /* renamed from: com.nianticlabs.pokemongoplus.SfidaPeripheral.1.5 */
        class C08075 implements Runnable {
            final /* synthetic */ BluetoothGattCharacteristic val$characteristic;
            final /* synthetic */ BluetoothGatt val$gatt;

            C08075(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
                this.val$gatt = bluetoothGatt;
                this.val$characteristic = bluetoothGattCharacteristic;
            }

            public void run() {
                Log.d(SfidaPeripheral.TAG, "onCharacteristicChanged");
                synchronized (SfidaPeripheral.this.serviceRef) {
                    Iterator it = SfidaPeripheral.this.serviceRef.iterator();
                    while (it.hasNext()) {
                        ((SfidaService) it.next()).onCharacteristicChanged(this.val$gatt, this.val$characteristic);
                    }
                }
            }
        }

        /* renamed from: com.nianticlabs.pokemongoplus.SfidaPeripheral.1.6 */
        class C08086 implements Runnable {
            final /* synthetic */ BluetoothGattDescriptor val$descriptor;
            final /* synthetic */ BluetoothGatt val$gatt;
            final /* synthetic */ int val$status;

            C08086(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
                this.val$gatt = bluetoothGatt;
                this.val$descriptor = bluetoothGattDescriptor;
                this.val$status = i;
            }

            public void run() {
                Log.d(SfidaPeripheral.TAG, "onDescriptorWrite");
                synchronized (SfidaPeripheral.this.serviceRef) {
                    Iterator it = SfidaPeripheral.this.serviceRef.iterator();
                    while (it.hasNext()) {
                        ((SfidaService) it.next()).onDescriptorWrite(this.val$gatt, this.val$descriptor, this.val$status);
                    }
                }
            }
        }

        C08091() {
        }

        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            Log.d(SfidaPeripheral.TAG, "onConnectionStateChange");
            SfidaPeripheral.this.serialExecutor.execute(new C08031(gatt, status, newState));
        }

        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            Log.d(SfidaPeripheral.TAG, "onServicesDiscovered");
            SfidaPeripheral.this.serialExecutor.execute(new C08042(gatt, status));
        }

        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            Log.d(SfidaPeripheral.TAG, "onCharacteristicRead");
            SfidaPeripheral.this.serialExecutor.execute(new C08053(gatt, characteristic, status));
        }

        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            Log.d(SfidaPeripheral.TAG, "onCharacteristicWrite");
            SfidaPeripheral.this.serialExecutor.execute(new C08064(gatt, characteristic, status));
        }

        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            Log.d(SfidaPeripheral.TAG, "onCharacteristicChanged");
            SfidaPeripheral.this.serialExecutor.execute(new C08075(gatt, characteristic));
        }

        public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
            Log.d(SfidaPeripheral.TAG, "onDescriptorWrite");
            SfidaPeripheral.this.serialExecutor.execute(new C08086(gatt, descriptor, status));
        }

        public void onMtuChanged(BluetoothGatt gatt, int mtu, int status) {
            super.onMtuChanged(gatt, mtu, status);
            Log.d(SfidaPeripheral.TAG, "onMthChanged mtu " + mtu + " status : " + status);
        }
    }

    /* renamed from: com.nianticlabs.pokemongoplus.SfidaPeripheral.2 */
    class C08122 implements Runnable {

        /* renamed from: com.nianticlabs.pokemongoplus.SfidaPeripheral.2.1 */
        class C08111 implements CompletionCallback {

            /* renamed from: com.nianticlabs.pokemongoplus.SfidaPeripheral.2.1.1 */
            class C08101 implements Runnable {
                final /* synthetic */ BluetoothError val$error;
                final /* synthetic */ boolean val$success;

                C08101(boolean z, BluetoothError bluetoothError) {
                    this.val$success = z;
                    this.val$error = bluetoothError;
                }

                public void run() {
                    Log.e(SfidaPeripheral.TAG, String.format("Tid: %d discoverServices() callback", new Object[]{Long.valueOf(Thread.currentThread().getId())}));
                    Log.d(SfidaPeripheral.TAG, "discoverServices success:" + this.val$success + " error:" + this.val$error);
                    SfidaPeripheral.this.nativeDiscoverServicesCallback(this.val$success, this.val$error.getInt());
                }
            }

            C08111() {
            }

            public void onCompletion(boolean success, BluetoothError error) {
                SfidaPeripheral.this.serialExecutor.execute(new C08101(success, error));
            }
        }

        C08122() {
        }

        public void run() {
            SfidaPeripheral.this.discoverServices(new C08111());
        }
    }

    /* renamed from: com.nianticlabs.pokemongoplus.SfidaPeripheral.3 */
    class C08143 implements Runnable {

        /* renamed from: com.nianticlabs.pokemongoplus.SfidaPeripheral.3.1 */
        class C08131 implements ConnectCallback {
            C08131() {
            }

            public void onConnectionStateChanged(boolean success, BluetoothError error) {
                Log.e(SfidaPeripheral.TAG, String.format("Tid: %d connect() onConnectionStateChanged()", new Object[]{Long.valueOf(Thread.currentThread().getId())}));
                if (!success) {
                    Log.e(SfidaPeripheral.TAG, String.format("connect() FAILED, state = %d, bond: %d, ERROR: %s", new Object[]{SfidaPeripheral.this.state.toString(), Integer.valueOf(SfidaPeripheral.this.bondState), error.toString()}));
                }
                SfidaPeripheral.this.nativeConnectCallback(success, error.getInt());
            }
        }

        C08143() {
        }

        public void run() {
            Log.e(SfidaPeripheral.TAG, String.format("Tid: %d connect()", new Object[]{Long.valueOf(Thread.currentThread().getId())}));
            Log.d(SfidaPeripheral.TAG, "connect() called, state = " + SfidaPeripheral.this.state.toString() + ", bond: " + SfidaPeripheral.this.bondState);
            SfidaPeripheral.this.connect(new C08131());
        }
    }

    /* renamed from: com.nianticlabs.pokemongoplus.SfidaPeripheral.4 */
    class C08164 implements Runnable {

        /* renamed from: com.nianticlabs.pokemongoplus.SfidaPeripheral.4.1 */
        class C08151 implements ConnectCallback {
            C08151() {
            }

            public void onConnectionStateChanged(boolean success, BluetoothError error) {
                Log.e(SfidaPeripheral.TAG, String.format("Tid: %d disconnect() onConnectionStateChanged", new Object[]{Long.valueOf(Thread.currentThread().getId())}));
                SfidaPeripheral.this.nativeDisconnectCallback(success, error.getInt());
            }
        }

        C08164() {
        }

        public void run() {
            Log.e(SfidaPeripheral.TAG, String.format("Tid: %d disconnect()", new Object[]{Long.valueOf(Thread.currentThread().getId())}));
            Log.d(SfidaPeripheral.TAG, "disconnect() called, state = " + SfidaPeripheral.this.state.toString() + ", bond: " + SfidaPeripheral.this.bondState);
            SfidaPeripheral.this.disconnect(new C08151());
        }
    }

    /* renamed from: com.nianticlabs.pokemongoplus.SfidaPeripheral.5 */
    class C08175 extends BroadcastReceiver {
        C08175() {
        }

        public void onReceive(Context context, Intent intent) {
            Log.d(SfidaPeripheral.TAG, "bluetoothReceiver onReceive");
            onHandleBluetoothIntent(intent);
        }

        private void onHandleBluetoothIntent(Intent intent) {
            String action = intent.getAction();
            if (action == null) {
                Log.d(SfidaPeripheral.TAG, "onReceived() action was null");
                return;
            }
            Log.d(SfidaPeripheral.TAG, "onReceived() action was " + action);
            Object obj = -1;
            switch (action.hashCode()) {
                case -223687943:
                    if (action.equals("android.bluetooth.device.action.PAIRING_REQUEST")) {
                        obj = 1;
                        break;
                    }
                    break;
                case 2116862345:
                    if (action.equals("android.bluetooth.device.action.BOND_STATE_CHANGED")) {
                        obj = null;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case C1562R.styleable.AdsAttrs_adSize /*0*/:
                    Log.d(SfidaPeripheral.TAG, "ACTION_BOND_STATE_CHANGED, state = " + SfidaPeripheral.this.state.toString() + ", bond: " + SfidaPeripheral.this.bondState);
                    SfidaPeripheral.this.onBondStateChanged(intent);
                case C1562R.styleable.LoadingImageView_imageAspectRatio /*1*/:
                    Log.d(SfidaPeripheral.TAG, "ACTION_PAIRING_REQUEST, state = " + SfidaPeripheral.this.state.toString() + ", bond: " + SfidaPeripheral.this.bondState);
                    SfidaPeripheral.this.onPairingRequest((BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE"));
                default:
                    Log.d(SfidaPeripheral.TAG, "onReceive() : Error unhandled: " + action);
            }
        }
    }

    /* renamed from: com.nianticlabs.pokemongoplus.SfidaPeripheral.6 */
    class C08186 implements Runnable {
        C08186() {
        }

        public void run() {
            SfidaPeripheral.this.retryConnect();
        }
    }

    /* renamed from: com.nianticlabs.pokemongoplus.SfidaPeripheral.7 */
    class C08197 implements Runnable {
        final /* synthetic */ BluetoothError val$err;
        final /* synthetic */ boolean val$isDisconnected;

        C08197(boolean z, BluetoothError bluetoothError) {
            this.val$isDisconnected = z;
            this.val$err = bluetoothError;
        }

        public void run() {
            SfidaPeripheral.this.nativeMonitorDisconnectCallback(this.val$isDisconnected, this.val$err.getInt());
        }
    }

    private native void nativeConnectCallback(boolean z, int i);

    private native void nativeDisconnectCallback(boolean z, int i);

    private native void nativeDiscoverService(SfidaService sfidaService);

    private native void nativeDiscoverServicesCallback(boolean z, int i);

    private native void nativeMonitorDisconnectCallback(boolean z, int i);

    static {
        TAG = SfidaPeripheral.class.getSimpleName();
    }

    public SfidaPeripheral(HandlerExecutor serialExecutor, Context context, BluetoothDevice bluetoothDevice, byte[] scanRecord) {
        this.serviceRef = new ArrayList();
        this.bluetoothGattCallback = new C08091();
        this.bluetoothReceiver = new C08175();
        Log.e(TAG, String.format("Tid: %d SfidaPeripheral()", new Object[]{Long.valueOf(Thread.currentThread().getId())}));
        this.context = context;
        this.bluetoothDevice = bluetoothDevice;
        this.state = PeripheralState.Disconnected;
        this.serialExecutor = serialExecutor;
        this.bluetoothAdapter = SfidaUtils.getBluetoothManager(context).getAdapter();
        this.scanRecord = scanRecord;
        this.bondState = 10;
    }

    public void onCreate() {
        Log.e(TAG, String.format("Tid: %d onCreate()", new Object[]{Long.valueOf(Thread.currentThread().getId())}));
        IntentFilter bluetoothIntentFilter = new IntentFilter();
        bluetoothIntentFilter.addAction("android.bluetooth.device.action.BOND_STATE_CHANGED");
        bluetoothIntentFilter.addAction("android.bluetooth.device.action.PAIRING_REQUEST");
        Log.d(TAG, "context.registerReceiver(bluetoothReceiver");
        this.context.registerReceiver(this.bluetoothReceiver, bluetoothIntentFilter);
    }

    public void onDestroy() {
        Log.e(TAG, String.format("Tid: %d onDestroy()", new Object[]{Long.valueOf(Thread.currentThread().getId())}));
        Log.d(TAG, "context.unregisterReceiver(bluetoothReceiver");
        try {
            this.context.unregisterReceiver(this.bluetoothReceiver);
        } catch (IllegalArgumentException e) {
            Log.d(TAG, "java.lang.IllegalArgumentException: Receiver not registered");
        }
        releaseServices();
    }

    public String getIdentifier() {
        return this.bluetoothDevice.getAddress();
    }

    public String getName() {
        return this.bluetoothDevice.getName();
    }

    public PeripheralState getState() {
        return this.state;
    }

    public int getStateInt() {
        return getState().getInt();
    }

    private byte[] byteArrayFromHexString(String s) {
        int len = s.length();
        byte[] data = new byte[(len / 2)];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public void setScanRecord(byte[] record) {
        synchronized (this) {
            this.scanRecord = record;
        }
    }

    public long getAdvertisingServiceDataLongValue(String uuid) {
        long j;
        synchronized (this) {
            byte[] uuidBytes = byteArrayFromHexString(uuid);
            int length = uuidBytes.length;
            for (int i = 0; i < this.scanRecord.length - length; i++) {
                boolean found = true;
                for (int j2 = 0; j2 < length; j2++) {
                    if (this.scanRecord[i + j2] != uuidBytes[(length - 1) - j2]) {
                        found = false;
                        break;
                    }
                }
                if (found) {
                    j = (long) this.scanRecord[i + length];
                    break;
                }
            }
            j = 0;
        }
        return j;
    }

    public void discoverServices(CompletionCallback callback) {
        this.serialExecutor.maybeAssertOnThread();
        Log.e(TAG, String.format("Tid: %d discoverServices()", new Object[]{Long.valueOf(Thread.currentThread().getId())}));
        Log.d(TAG, "discoverServices(" + callback.toString() + ")");
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (this.gatt != null) {
            this.discoverServicesCallback = callback;
            Log.d(TAG, "discoverServices:" + this.gatt.discoverServices());
            return;
        }
        Log.e(TAG, "gatt is null");
    }

    public void discoverServices() {
        this.serialExecutor.execute(new C08122());
    }

    public void connect() {
        this.serialExecutor.execute(new C08143());
    }

    public void disconnect() {
        this.serialExecutor.execute(new C08164());
    }

    public int getServiceCount() {
        return this.serviceRef.size();
    }

    public Service getService(int index) {
        if (index > getServiceCount() - 1) {
            return null;
        }
        return (Service) this.serviceRef.get(index);
    }

    public Service getService(String uuid) {
        if (uuid != null) {
            int count = getServiceCount();
            for (int i = 0; i < count; i++) {
                Service service = getService(i);
                if (uuid.equals(service.getUuid())) {
                    return service;
                }
            }
        }
        return null;
    }

    private void retryConnect() {
        this.serialExecutor.maybeAssertOnThread();
        Log.e(TAG, String.format("Tid: %d retryConnect", new Object[]{Long.valueOf(Thread.currentThread().getId())}));
        if (isBoundDevice(this.bluetoothDevice).booleanValue()) {
            this.bondState = 12;
            String address = this.bluetoothDevice.getAddress();
            if (this.bluetoothAdapter == null || address == null) {
                Log.w(TAG, "[BLE] BluetoothAdapter not initialized or unspecified address.");
                this.state = PeripheralState.Disconnected;
                return;
            } else if (!address.equals(this.bluetoothDevice.getAddress()) || this.gatt == null) {
                this.gatt = this.bluetoothDevice.connectGatt(this.context, true, this.bluetoothGattCallback);
                Log.d(TAG, "Trying to create a new connection.");
                return;
            } else {
                Log.d(TAG, "[BLE] Trying to use an existing bluetoothGatt for connection.");
                this.gatt.connect();
                return;
            }
        }
        Log.d(TAG, "Create bond.");
        this.bondState = 10;
        SfidaUtils.createBond(this.bluetoothDevice);
    }

    public void connect(ConnectCallback callback) {
        this.serialExecutor.maybeAssertOnThread();
        this.connectCallback = callback;
        this.state = PeripheralState.Connecting;
        Log.d(TAG, String.format("SfidaPeripheral connect. state = %s", new Object[]{this.state.toString()}));
        if (isBoundDevice(this.bluetoothDevice).booleanValue()) {
            unpairDevice();
        }
        retryConnect();
    }

    public void disconnect(ConnectCallback callback) {
        this.serialExecutor.maybeAssertOnThread();
        Log.d(TAG, String.format("Tid: %d disconnect(ConnectCallback)", new Object[]{Long.valueOf(Thread.currentThread().getId())}));
        this.state = PeripheralState.Disconnecting;
        Log.d(TAG, String.format("SfidaPeripheral disconnect. state = %s", new Object[]{this.state.toString()}));
        if (this.gatt != null) {
            this.disconnectCallback = callback;
            this.gatt.disconnect();
        } else {
            Log.w(TAG, "Gatt is null");
            callback.onConnectionStateChanged(true, BluetoothError.Unknown);
        }
        unpairDevice();
    }

    public void closeBluetoothGatt() {
        Log.d(TAG, String.format("Tid: %d closeBluetoothGatt()", new Object[]{Long.valueOf(Thread.currentThread().getId())}));
        Log.d(TAG, "closeBluetoothGatt, state = " + this.state.toString() + ", bond: " + this.bondState);
        if (this.gatt != null) {
            this.gatt.close();
            this.gatt = null;
        }
    }

    private void releaseServices() {
        Log.e(TAG, String.format("Tid: %d releaseServices()", new Object[]{Long.valueOf(Thread.currentThread().getId())}));
        Log.d(TAG, "releaseServices, state = " + this.state.toString() + ", bond: " + this.bondState);
        synchronized (this.serviceRef) {
            Iterator it = this.serviceRef.iterator();
            while (it.hasNext()) {
                Service service = (Service) it.next();
                if (service instanceof SfidaService) {
                    ((SfidaService) service).onDestroy();
                }
            }
            this.serviceRef.clear();
        }
    }

    private Boolean isBoundDevice(BluetoothDevice device) {
        Set<BluetoothDevice> bondedDevices = this.bluetoothAdapter.getBondedDevices();
        if (!(bondedDevices == null || bondedDevices.size() == 0)) {
            for (BluetoothDevice bondedDevice : bondedDevices) {
                if (bondedDevice.getAddress().equals(device.getAddress())) {
                    return Boolean.valueOf(true);
                }
            }
        }
        return Boolean.valueOf(false);
    }

    private void reconnnectFromBonding(BluetoothDevice device) {
        Log.d(TAG, "reconnnectFromBonding()");
        this.serialExecutor.execute(new C08186());
    }

    private void unpairDevice() {
        Log.e(TAG, String.format("Tid: %d unpairDevice()", new Object[]{Long.valueOf(Thread.currentThread().getId())}));
        Log.d(TAG, "unpairDevice()");
        try {
            this.bluetoothDevice.getClass().getMethod("removeBond", (Class[]) null).invoke(this.bluetoothDevice, (Object[]) null);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    private void disconnectFromBonding(BluetoothDevice device) {
        Log.d(TAG, "disconnectFromBonding()");
        SfidaUtils.createBond(this.bluetoothDevice);
    }

    private void bondingCanceled(BluetoothDevice device) {
        Log.d(TAG, "bondingCanceled()");
        SfidaUtils.createBond(device);
    }

    private void onPairingRequest(BluetoothDevice device) {
        Log.e(TAG, String.format("Tid: %d onPairingRequest()", new Object[]{Long.valueOf(Thread.currentThread().getId())}));
        Log.d(TAG, "onPairingRequest()");
        try {
            device.getClass().getMethod("setPairingConfirmation", new Class[]{Boolean.TYPE}).invoke(device, new Object[]{Boolean.valueOf(true)});
            device.getClass().getMethod("cancelPairingUserInput", new Class[0]).invoke(device, new Object[0]);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
        }
    }

    private void onBondStateChanged(Intent intent) {
        int newState = intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", ExploreByTouchHelper.INVALID_ID);
        int oldState = intent.getIntExtra("android.bluetooth.device.extra.PREVIOUS_BOND_STATE", ExploreByTouchHelper.INVALID_ID);
        Log.e(TAG, String.format("Tid: %d onBondStateChanged()", new Object[]{Long.valueOf(Thread.currentThread().getId())}));
        Log.d(TAG, "[BLE] ACTION_BOND_STATE_CHANGED oldState : " + SfidaUtils.getBondStateName(oldState) + " \u2192 newState : " + SfidaUtils.getBondStateName(newState) + " local state: " + SfidaUtils.getBondStateName(this.bondState));
        BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        if (device != null) {
            this.bondState = newState;
            switch (newState) {
                case Place.TYPE_BEAUTY_SALON /*10*/:
                    if (oldState == 12) {
                        Log.d(TAG, "BOND_NONE, disconnecting from bonding, state = " + this.state.toString() + ", bond: " + this.bondState);
                        if (this.state == PeripheralState.Connecting || this.state == PeripheralState.Connected) {
                            disconnectFromBonding(device);
                        }
                    } else if (oldState == 11) {
                        bondingCanceled(device);
                        Log.d(TAG, "BOND_NONE, bonding canceled, state = " + this.state.toString() + ", bond: " + this.bondState);
                    } else {
                        Log.d(TAG, "Unhandled oldState : " + oldState);
                    }
                case Place.TYPE_BOOK_STORE /*12*/:
                    Log.d(TAG, "BOND_BONDED, state = " + this.state.toString() + ", bond: " + this.bondState);
                    if (!tryCompleteConnect() && this.state != PeripheralState.Disconnected && this.state != PeripheralState.Disconnecting) {
                        Log.d(TAG, "BOND_BONDED, retrying, state = " + this.state.toString() + ", bond: " + this.bondState);
                        reconnnectFromBonding(device);
                    }
                default:
            }
        }
    }

    boolean tryCompleteConnect() {
        Log.d(TAG, "tryCompleteConnect, state = " + this.state.toString() + ", bond: " + this.bondState);
        Log.e(TAG, String.format("Tid: %d tryCompleteConnect()", new Object[]{Long.valueOf(Thread.currentThread().getId())}));
        if (this.bondState != 12 || this.state != PeripheralState.Connected || this.connectCallback == null) {
            return false;
        }
        Log.d(TAG, String.format("calling onConnectionStateChanged. state = %s", new Object[]{this.state.toString()}));
        this.connectCallback.onConnectionStateChanged(true, BluetoothError.Unknown);
        this.connectCallback = null;
        return true;
    }

    public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
        boolean z = true;
        switch (newState) {
            case C1562R.styleable.AdsAttrs_adSize /*0*/:
                Log.e(TAG, String.format("Tid: %d onConnectionStateChange(STATE_DISCONNECTED)", new Object[]{Long.valueOf(Thread.currentThread().getId())}));
                Log.d(TAG, "Disconnected from GATT server., state = " + this.state.toString());
                SfidaUtils.refreshDeviceCache(gatt);
                this.bondState = 10;
                closeBluetoothGatt();
                releaseServices();
                if (this.disconnectCallback != null) {
                    this.state = PeripheralState.Disconnected;
                    Log.d(TAG, "Disconnected from state " + this.state.toString());
                    ConnectCallback connectCallback = this.disconnectCallback;
                    if (status != 0) {
                        z = false;
                    }
                    connectCallback.onConnectionStateChanged(z, BluetoothError.Unknown);
                } else if (this.connectCallback == null || !(this.state == PeripheralState.Connected || this.state == PeripheralState.Connecting)) {
                    Log.d(TAG, "Disconnected after connected, giving up from state " + this.state.toString());
                    this.state = PeripheralState.Disconnected;
                    callMonitorDisconnectCallback(true, BluetoothError.PeripheralDisconnected);
                } else {
                    Log.d(TAG, "Disconnected, retrying from state " + this.state.toString());
                    this.state = PeripheralState.Connecting;
                    retryConnect();
                }
            case C1562R.styleable.LoadingImageView_circleCrop /*2*/:
                Log.d(TAG, "Connected with GATT server.");
                Log.e(TAG, String.format("Tid: %d onConnectionStateChange(STATE_CONNECTED)", new Object[]{Long.valueOf(Thread.currentThread().getId())}));
                this.state = PeripheralState.Connected;
                Log.d(TAG, String.format("CONNECTED SfidaPeripheral connect GATT callback. state = %s", new Object[]{this.state.toString()}));
                tryCompleteConnect();
            default:
                Log.e(TAG, String.format("Tid: %d onConnectionStateChange(default)", new Object[]{Long.valueOf(Thread.currentThread().getId())}));
                Log.e(TAG, "onConnectionStateChange() UnhandledState status : " + status + " " + "newState : " + newState);
        }
    }

    private void callMonitorDisconnectCallback(boolean isDisconnected, BluetoothError err) {
        this.serialExecutor.execute(new C08197(isDisconnected, err));
    }

    public void onServicesDiscovered(BluetoothGatt gatt, int status) {
        Log.e(TAG, String.format("Tid: %d onServicesDiscovered(default)", new Object[]{Long.valueOf(Thread.currentThread().getId())}));
        switch (status) {
            case C1562R.styleable.AdsAttrs_adSize /*0*/:
                List<BluetoothGattService> services = gatt.getServices();
                synchronized (this.serviceRef) {
                    Log.e(TAG, "onServicesDiscovered thread:" + Thread.currentThread().getId());
                    this.serviceRef.clear();
                    for (BluetoothGattService service : services) {
                        SfidaService sfidaService = new SfidaService(this.serialExecutor, service, gatt);
                        nativeDiscoverService(sfidaService);
                        this.serviceRef.add(sfidaService);
                    }
                    break;
                }
                if (this.discoverServicesCallback != null) {
                    this.discoverServicesCallback.onCompletion(true, BluetoothError.Unknown);
                    return;
                }
                Log.e(TAG, String.format("onServicesDiscovered() no callback when discover %d service on device %s", new Object[]{Integer.valueOf(services.size()), this.bluetoothDevice.getAddress()}));
            default:
                Log.e(TAG, "DISCONNECTED: BluetoothError, onServicesDiscovered, from state " + this.state.toString());
                this.discoverServicesCallback.onCompletion(false, BluetoothError.Unknown);
                Log.e(TAG, "[BLE] onServicesDiscovered received error: " + status);
        }
    }

    public String getAddress() {
        return this.bluetoothDevice.getAddress();
    }
}
