package com.gio.mscuentas.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.security.KeyPairGeneratorSpec;
import android.util.Base64;
import android.util.Log;
import com.gio.mscuentas.R;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.Calendar;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.security.auth.x500.X500Principal;


public class KeyStoreHelper {

    private static final String TAG = KeyStoreHelper.class.getSimpleName();
    private static final String AndroidKeyStore = "AndroidKeyStore";
    private static KeyStoreHelper sharedInstance = null;
    private Context context;

    KeyStore keyStore;

    private KeyStoreHelper() {

    }

    public static KeyStoreHelper getInstance() {
        if (sharedInstance == null)
            sharedInstance = new KeyStoreHelper();
        return sharedInstance;
    }

    public void init(Context context) {
        this.context = context.getApplicationContext();
        try {
            keyStore = KeyStore.getInstance(AndroidKeyStore);
            keyStore.load(null);
            String alias = context.getString(R.string.key_store_alias);
            // Create new key if needed
            if (!keyStore.containsAlias(alias)) {
                Calendar start = Calendar.getInstance();
                Calendar end = Calendar.getInstance();
                end.add(Calendar.YEAR, 1);
                KeyPairGeneratorSpec spec = new KeyPairGeneratorSpec.Builder(context)
                        .setAlias(alias)
                        .setSubject(new X500Principal("CN=Sample Name, O=Android Authority"))
                        .setSerialNumber(BigInteger.ONE)
                        .setStartDate(start.getTime())
                        .setEndDate(end.getTime())
                        .build();
                KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "AndroidKeyStore");
                generator.initialize(spec);
                generator.generateKeyPair();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, Log.getStackTraceString(e));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            Log.e(TAG, Log.getStackTraceString(e));
        } catch (CertificateException e) {
            e.printStackTrace();
            Log.e(TAG, Log.getStackTraceString(e));
        } catch (KeyStoreException e) {
            e.printStackTrace();
            Log.e(TAG, Log.getStackTraceString(e));
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
            Log.e(TAG, Log.getStackTraceString(e));
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
            Log.e(TAG, Log.getStackTraceString(e));
        }
    }

    public boolean savePin(String pin) {
        try {
            String alias = context.getString(R.string.key_store_alias);
            KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry)keyStore.getEntry(alias, null);
            RSAPublicKey publicKey = (RSAPublicKey) privateKeyEntry.getCertificate().getPublicKey();

            // Encrypt the text
            Cipher input = Cipher.getInstance("RSA/ECB/PKCS1Padding", "AndroidOpenSSL");
            input.init(Cipher.ENCRYPT_MODE, publicKey);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            CipherOutputStream cipherOutputStream = new CipherOutputStream(outputStream, input);
            cipherOutputStream.write(pin.getBytes("UTF-8"));
            cipherOutputStream.close();

            byte [] vals = outputStream.toByteArray();
            String encryptedKeyBase64 = Base64.encodeToString(vals, Base64.DEFAULT);
            SharedPreferences pref = context.getSharedPreferences(context.getString(R.string.shared_preferences_name), Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = pref.edit();
            edit.putString(context.getString(R.string.shared_preferences_key_pin), encryptedKeyBase64);
            edit.commit();
            return true;
        } catch (Exception e) {
            Log.e(TAG, Log.getStackTraceString(e));
            return false;
        }
    }

    public String readPin() {
        try {
            String alias = context.getString(R.string.key_store_alias);
            KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry)keyStore.getEntry(alias, null);

            Cipher output = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            output.init(Cipher.DECRYPT_MODE, privateKeyEntry.getPrivateKey());

            SharedPreferences pref = context.getSharedPreferences(context.getString(R.string.shared_preferences_name), Context.MODE_PRIVATE);
            String cipherText = pref.getString(context.getString(R.string.shared_preferences_key_pin), null);
            if (cipherText != null) {
                CipherInputStream cipherInputStream = new CipherInputStream(
                        new ByteArrayInputStream(Base64.decode(cipherText, Base64.DEFAULT)), output);
                ArrayList<Byte> values = new ArrayList<>();
                int nextByte;
                while ((nextByte = cipherInputStream.read()) != -1) {
                    values.add((byte)nextByte);
                }

                byte[] bytes = new byte[values.size()];
                for(int i = 0; i < bytes.length; i++) {
                    bytes[i] = values.get(i).byteValue();
                }

                String finalText = new String(bytes, 0, bytes.length, "UTF-8");
                return finalText;
            }
            return "";
        } catch (Exception e) {
            Log.e(TAG, Log.getStackTraceString(e));
            return "";
        }
    }

    public boolean saveToken(String token) {
        try {
            String alias = context.getString(R.string.key_store_alias);
            KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry)keyStore.getEntry(alias, null);
            RSAPublicKey publicKey = (RSAPublicKey) privateKeyEntry.getCertificate().getPublicKey();

            // Encrypt the text
            Cipher input = Cipher.getInstance("RSA/ECB/PKCS1Padding", "AndroidOpenSSL");
            input.init(Cipher.ENCRYPT_MODE, publicKey);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            CipherOutputStream cipherOutputStream = new CipherOutputStream(outputStream, input);
            cipherOutputStream.write(token.getBytes("UTF-8"));
            cipherOutputStream.close();

            byte [] vals = outputStream.toByteArray();
            String encryptedKeyBase64 = Base64.encodeToString(vals, Base64.DEFAULT);
            SharedPreferences pref = context.getSharedPreferences(context.getString(R.string.shared_preferences_name), Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = pref.edit();
            edit.putString(context.getString(R.string.shared_preferences_key_token), encryptedKeyBase64);
            edit.commit();
            return true;
        } catch (Exception e) {
            Log.e(TAG, Log.getStackTraceString(e));
            return false;
        }
    }

    public String readToken() {
        try {
            String alias = context.getString(R.string.key_store_alias);
            KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry)keyStore.getEntry(alias, null);

            Cipher output = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            output.init(Cipher.DECRYPT_MODE, privateKeyEntry.getPrivateKey());

            SharedPreferences pref = context.getSharedPreferences(context.getString(R.string.shared_preferences_name), Context.MODE_PRIVATE);
            String cipherText = pref.getString(context.getString(R.string.shared_preferences_key_token), null);
            if (cipherText != null) {
                CipherInputStream cipherInputStream = new CipherInputStream(
                        new ByteArrayInputStream(Base64.decode(cipherText, Base64.DEFAULT)), output);
                ArrayList<Byte> values = new ArrayList<>();
                int nextByte;
                while ((nextByte = cipherInputStream.read()) != -1) {
                    values.add((byte)nextByte);
                }

                byte[] bytes = new byte[values.size()];
                for(int i = 0; i < bytes.length; i++) {
                    bytes[i] = values.get(i).byteValue();
                }

                String finalText = new String(bytes, 0, bytes.length, "UTF-8");
                return finalText;
            }
            return "";
        } catch (Exception e) {
            Log.e(TAG, Log.getStackTraceString(e));
            return "";
        }
    }

    public void deleteToken() {
        SharedPreferences pref = context.getSharedPreferences(context.getString(R.string.shared_preferences_name), Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.remove(context.getString(R.string.shared_preferences_key_token));
        edit.commit();
    }
}
