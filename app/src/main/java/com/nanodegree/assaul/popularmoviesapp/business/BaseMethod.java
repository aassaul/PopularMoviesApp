package com.nanodegree.assaul.popularmoviesapp.business;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.nanodegree.assaul.popularmoviesapp.business.enums.RequestMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.nanodegree.assaul.popularmoviesapp.log.LogConstants.CODE_USAGE;
import static com.nanodegree.assaul.popularmoviesapp.log.LogConstants.TAG;
import static com.nanodegree.assaul.popularmoviesapp.log.LogConstants.COMMUNICATION_MESSAGE;

/**
 * Created by Andrey Assaul on 28.06.2015.
 */
public abstract class BaseMethod<T extends Enum, Result> {
    @NonNull
    private final Map<T, BaseParameter<T, ?>> parameterMap;
    @NonNull
    private final String baseUrl;
    @NonNull
    private final RequestMethod requestMethod;
    @Nullable
    private Result result;

    public BaseMethod(@NonNull RequestMethod requestMethod, @NonNull String baseUrl, @NonNull Collection<BaseParameter<T, ?>> parameters) {
        parameterMap = new HashMap<>();
        for (BaseParameter<T, ?> parameter : parameters){
            parameterMap.put(parameter.getType(),parameter);
        }
        this.baseUrl = (parameters.size() == 0)?baseUrl:(baseUrl+"?");
        this.requestMethod = requestMethod;
    }

    @Nullable
    public final Result getResult() {
        return result;
    }

    public final void run(){
        result = null;
        switch (requestMethod){
            case GET:
                sendGET();
                break;
            case POST:
                break;
        }
    }

    protected void onFault(String url, int responseCode){
        Log.e(TAG, COMMUNICATION_MESSAGE.getResponseFault(url, responseCode));
    }

    protected abstract void onResult(@NonNull StringBuilder response);

    @Nullable
    protected final BaseParameter<T, ?> getParameterForType(@NonNull T type){
        if (!parameterMap.containsKey(type)){
            Log.w(TAG, CODE_USAGE.getIllegalParameterValueError("type", type.toString(), "getParameterForType", getClass().getName()));
            return null;
        }
        return parameterMap.get(type);
    }

    protected final void setResult(@Nullable Result result) {
        this.result = result;
    }

    private void sendGET() {
        Collection<BaseParameter<T,?>> parameters = parameterMap.values();
        String requestUrl = baseUrl;
        if (parameters.size() > 0){
            requestUrl += TextUtils.join("&", parameters);
        }
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            try {
                connection.setRequestMethod(RequestMethod.GET.name());
                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    StringBuilder response = readStreamFromConnection(connection);
                    if (response != null){
                        Log.i(TAG, COMMUNICATION_MESSAGE.getResponseSuccessMessage(requestUrl));
                        onResult(response);
                    }
                }else {
                    onFault(requestUrl, responseCode);
                }
            } catch (ProtocolException e){
                Log.e(TAG, COMMUNICATION_MESSAGE.getMethodIsNotSupportedError(RequestMethod.GET.name(), requestUrl));
            } catch (IOException e){
                Log.e(TAG, COMMUNICATION_MESSAGE.getCannotGetResponseCode(requestUrl));
            } finally {
                connection.disconnect();
            }
        } catch (MalformedURLException e){
            Log.e(TAG, COMMUNICATION_MESSAGE.getBadUrlError(requestUrl));
        } catch (IOException e){
            Log.e(TAG, COMMUNICATION_MESSAGE.getCannotOpenConnectionError(requestUrl));
        }
    }

    @Nullable
    private StringBuilder readStreamFromConnection(@NonNull URLConnection connection){
        StringBuilder response = null;
        try {
            InputStream inputStream = connection.getInputStream();
            BufferedReader inputStreamReader = new BufferedReader(new InputStreamReader(inputStream));
            try {
                String inputLine = inputStreamReader.readLine();
                response = new StringBuilder();
                while (inputLine != null) {
                    response.append(inputLine);
                    inputLine = inputStreamReader.readLine();
                }
            } catch (IOException e){
                Log.e(TAG, COMMUNICATION_MESSAGE.getCannotReadInputErrorPattern(connection.getURL().toString()));
            } finally {
                inputStreamReader.close();
            }
        }catch (IOException e){
            Log.e(TAG, COMMUNICATION_MESSAGE.getCannotEstablishInputStreamError(connection.getURL().toString()));
        }
        return response;
    }
}