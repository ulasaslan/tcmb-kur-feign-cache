package ulas.tcmbkurfeigncache.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ulas.tcmbkurfeigncache.entity.TCMBResponse;

@FeignClient(value = "tcmbKurlarClient", url="https://tcmb.gov.tr/kurlar" )
public interface TcmbKurClient {

    @RequestMapping(method = RequestMethod.GET, value = "/{date}.xml")
    TCMBResponse getKurByDate(@PathVariable String date);




}
