package ulas.tcmbkurfeigncache.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ulas.tcmbkurfeigncache.entity.TCMBResponse;
import ulas.tcmbkurfeigncache.service.TcmbKurService;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class TcmbKurController {

    final TcmbKurService tcmbKurService;

    @GetMapping("/kur/{year}/{month}/{day}")
    public TCMBResponse getToday(@PathVariable String year, @PathVariable String month, @PathVariable String day) {
        return tcmbKurService.getKurByDate(year+month + "/" + day+month+year);
    }


}
