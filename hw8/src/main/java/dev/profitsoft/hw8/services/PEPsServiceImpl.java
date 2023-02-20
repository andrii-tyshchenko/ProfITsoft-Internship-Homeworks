package dev.profitsoft.hw8.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import dev.profitsoft.hw8.data.PEPData;
import dev.profitsoft.hw8.dtos.PEPBasicInfoDto;
import dev.profitsoft.hw8.dtos.PEPFullNameQueryDto;
import dev.profitsoft.hw8.dtos.PEPsTopFirstNamesDto;
import dev.profitsoft.hw8.repositories.PEPsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class PEPsServiceImpl implements PEPsService {
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    private final PEPsRepository pepsRepository;

    @Autowired
    public PEPsServiceImpl(PEPsRepository pepsRepository) {
        this.pepsRepository = pepsRepository;
    }

    @Override
    public void addPEPsFromUploadedZipFileToDB(MultipartFile multipartFile) throws IOException {
        clearPEPsCollection();

        try (ZipInputStream zis = new ZipInputStream(multipartFile.getInputStream());
             BufferedReader br = new BufferedReader(new InputStreamReader(zis))) {
            ZipEntry zipEntry = zis.getNextEntry();

            while (zipEntry != null) {
                if (!zipEntry.isDirectory() && zipEntry.getName().endsWith(".json")) {
                    StringBuilder json = new StringBuilder();

                    String inputString;

                    while((inputString = br.readLine()) != null) {
                        json.append(inputString);
                    }

                    BasicDBObject[] pepsBDBOArray = JSON_MAPPER.readValue(json.toString(), BasicDBObject[].class);

                    for (BasicDBObject basicDBObject : pepsBDBOArray) {
                        pepsRepository.insertBasicDBObject(basicDBObject);
                    }
                }

                zipEntry = zis.getNextEntry();
            }

            zis.closeEntry();
        }
    }

    @Override
    public List<PEPBasicInfoDto> findByFullNameUA(PEPFullNameQueryDto query) {
        List<PEPData> peps = pepsRepository.findByFirstNameUAAndPatronymicAndLastNameUA(
                query.getFirstName(),
                query.getPatronymic(),
                query.getLastName()
        );

        return peps.stream()
                .map(this::convertToPEPBasicInfoDto)
                .toList();
    }

    @Override
    public List<PEPsTopFirstNamesDto> getTopFirstNamesByQuantityWhereIsPepTrue(int limit) {
        return pepsRepository.getTopFirstNamesAndTheirQuantityWhereIsPepTrue(limit);
    }

    /**
     * Deletes all documents in a collection of PEPs.
     */
    private void clearPEPsCollection() {
        pepsRepository.deleteAll();
    }

    /**
     * Converts given {@code PEPData} object to a {@code PEPBasicInfoDto} object.
     * @param pep object, which should be converted
     * @return {@code PEPBasicInfoDto} object with fields values, taken from the given {@code PEPData} object
     */
    private PEPBasicInfoDto convertToPEPBasicInfoDto(PEPData pep) {
        PEPBasicInfoDto pepBasicInfo = new PEPBasicInfoDto();
        pepBasicInfo.setFirstNameUA(pep.getFirstNameUA());
        pepBasicInfo.setPatronymic(pep.getPatronymic());
        pepBasicInfo.setLastNameUA(pep.getLastNameUA());
        pepBasicInfo.setDateOfBirth(pep.getDateOfBirth());
        pepBasicInfo.setDied(pep.getDied());
        pepBasicInfo.setIsPEP(pep.getIsPEP());
        pepBasicInfo.setTypeOfOfficialUA(pep.getTypeOfOfficialUA());
        pepBasicInfo.setLastWorkplaceUA(pep.getLastWorkplaceUA());
        pepBasicInfo.setLastJobTitleUA(pep.getLastJobTitleUA());
        pepBasicInfo.setUrl(pep.getUrl());

        return pepBasicInfo;
    }
}