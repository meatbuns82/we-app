package com.luwh.we.app.core.cache;

import com.luwh.we.app.common.constants.Constants;
import com.luwh.we.app.core.properties.PictureProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lu.wh
 * @date 2023/10/12 14/41/27
 * @description
 */
@Component
public class PictureCacheManager {
    private Logger logger = LoggerFactory.getLogger(PictureCacheManager.class);
    @Resource
    private PictureProperties properties;
    private final ConcurrentHashMap<String, String> cache = new ConcurrentHashMap<>();

    @PostConstruct
    private void init() {
        loadCache();
    }

    public String writeToLocalAndCache(String key, byte[] bytes, String fileSuffix) {
        String path = properties.getCachePath() + Constants.FILE_SEPERATOR + key + fileSuffix;
        try (BufferedOutputStream bos = new BufferedOutputStream(
                new FileOutputStream(path));) {
            bos.write(bytes);
        } catch (Exception e) {
            logger.info("cache file exception, cause:{}", e);
            return path;
        }
        cache.putIfAbsent(key, path);
        return path;
    }

    public boolean cacheExist(String key) {
        return cache.contains(key);
    }

    public boolean deleteCache() {

        return false;
    }

    public void loadCache() {
        File file = new File(properties.getCachePath());
        if (file.exists()) {
            File[] cacheFiles = file.listFiles();
            for (File cacheFile : cacheFiles) {
                cache.putIfAbsent(cacheFile.getName(), cacheFile.getAbsolutePath());
            }
        }else {
            file.mkdirs();
        }
    }

    public String getLocalCachePath(String key) {
        return cache.get(key);
    }
}
