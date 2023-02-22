package com.example.greenlight.data.responses;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Resource<T> {
    public static final int INITIAL = 0;
    public static final int LOADING = 1;
    public static final int SUCCESS = 2;
    public static final int FAILURE = 3;


    private final ApiResponses type;
    private final T data;
    private final Throwable error;

    private Resource(@NonNull ApiResponses type, @Nullable T d, @Nullable Throwable e){
        this.type = type;
        this.data = d;
        this.error = e;
    }

    public static <T> Resource<T> initial(){
        return new Resource<>(ApiResponses.INITIAL,null,null);
    }

    public static <T> Resource<T> loading(){
        return new Resource<>(ApiResponses.LOADING,null,null);
    }
    public static <T> Resource<T> success(T d){
        return new Resource<>(ApiResponses.SUCCESS,d,null);
    }

    public static <T> Resource<T> error(Throwable e){
        return new Resource<>(ApiResponses.ERROR,null,e);
    }

    public ApiResponses getType() {
        return type;
    }

    public T getData() {
        return data;
    }

    public Throwable getError() {
        return error;
    }
}
