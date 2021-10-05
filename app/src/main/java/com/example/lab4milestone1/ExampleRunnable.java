package com.example.lab4milestone1;

import com.example.lab4milestone1.MainActivity;

public class ExampleRunnable extends MainActivity implements Runnable {

    @Override
    public void run() {
        mockFileDownloader();
    }
}