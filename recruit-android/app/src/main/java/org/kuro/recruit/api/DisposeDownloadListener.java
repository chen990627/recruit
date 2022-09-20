package org.kuro.recruit.api;

/**
 * 监听下载进度
 */
public interface DisposeDownloadListener extends DisposeDataListener {
    void onProgress(int progress);
}
