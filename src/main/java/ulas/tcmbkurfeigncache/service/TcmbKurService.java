package ulas.tcmbkurfeigncache.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ulas.tcmbkurfeigncache.client.TcmbKurClient;
import ulas.tcmbkurfeigncache.entity.TCMBResponse;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"TcmbKurCache"})
public class TcmbKurService {

    final TcmbKurClient tcmbKurClient;


    @Cacheable(value = "TDBS-kurlar", key = "#date")
    public TCMBResponse getKurByDate(String date) {
        return tcmbKurClient.getKurByDate(date);
    }
}
