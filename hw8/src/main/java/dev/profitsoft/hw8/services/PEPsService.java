package dev.profitsoft.hw8.services;

import dev.profitsoft.hw8.dtos.PEPBasicInfoDto;
import dev.profitsoft.hw8.dtos.PEPFullNameQueryDto;
import dev.profitsoft.hw8.dtos.PEPsTopFirstNamesDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PEPsService {
    /**
     * Reads ZIP-file from given MultipartFile, parses it and saves read data to PEPs database.
     * Clears PEPs database before parsing given file.
     * @param multipartFile uploaded ZIP-file with JSON data, representing PEPs
     * @throws IOException
     */
    void addPEPsFromUploadedZipFileToDB(MultipartFile multipartFile) throws IOException;

    /**
     * Returns list of PEPs, converted to {@code PEPBasicInfoDto} objects, that match requirements of a given query.
     * This query's fields represent full name of a PEP (in Ukrainian) to be found.
     * @param query object, which fields' values are the parameters for the search query
     * @return list of PEPs, represented as {@code PEPBasicInfoDto} objects, matching query's requirements (same full name)
     */
    List<PEPBasicInfoDto> findByFullNameUA(PEPFullNameQueryDto query);

    /**
     * Returns list of {@code PEPsTopFirstNamesDto} objects that represent N most popular PEPs' first names
     * and total count of their appearance. PEP should have "isPEP" field value equal "true" to be counted.
     * N is represented by given "limit" argument.
     * @param limit number of most popular names to be shown
     * @return most popular first names of PEPs and count of their appearance
     */
    List<PEPsTopFirstNamesDto> getTopFirstNamesByQuantityWhereIsPepTrue(int limit);
}