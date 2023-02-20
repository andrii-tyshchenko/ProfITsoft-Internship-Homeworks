package dev.profitsoft.hw8.controllers;

import dev.profitsoft.hw8.dtos.PEPBasicInfoDto;
import dev.profitsoft.hw8.dtos.PEPFullNameQueryDto;
import dev.profitsoft.hw8.dtos.PEPsTopFirstNamesDto;
import dev.profitsoft.hw8.services.PEPsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/peps")
public class PEPsController {
    private final PEPsService pepsService;

    @Autowired
    public PEPsController(PEPsService pepsService) {
        this.pepsService = pepsService;
    }

    @PostMapping("/upload")
    public void uploadZipFileWithPEPs(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        pepsService.addPEPsFromUploadedZipFileToDB(multipartFile);
    }

    @PostMapping("/_search")
    public List<PEPBasicInfoDto> searchBySurnameNamePatronymic(@RequestBody PEPFullNameQueryDto query) {
       return pepsService.findByFullNameUA(query);
    }

    @GetMapping("/top_first_names")
    public List<PEPsTopFirstNamesDto> getTopPEPNamesAndTheirQuantityWhereIsPepTrue() {
        int limit = 10;

        return pepsService.getTopFirstNamesByQuantityWhereIsPepTrue(limit);
    }
}