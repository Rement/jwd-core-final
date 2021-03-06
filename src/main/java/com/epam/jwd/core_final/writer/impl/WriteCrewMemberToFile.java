package com.epam.jwd.core_final.writer.impl;

import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.writer.WriterToFile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteCrewMemberToFile implements WriterToFile<CrewMember> {
    private ApplicationProperties applicationProperties = new ApplicationProperties();
    private String dir = applicationProperties.getRootDir();
    String fileNameWhole = null;
    private String dirName;
    String fileName;
    private static WriteCrewMemberToFile instance;

    private WriteCrewMemberToFile() {
    }

    public static WriteCrewMemberToFile getInstance() {
        if (instance == null) {
            instance = new WriteCrewMemberToFile();
        }
        return instance;
    }

    @Override
    public void writeToFile(CrewMember crewMember) {
        fileName = applicationProperties.getCrewFileName();
        dirName = applicationProperties.getInputRootDir();
        fileNameWhole = dir + "/" + dirName + "/" + fileName;
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileNameWhole, true))) {
            bufferedWriter.write(crewMember.getRole() + "," + crewMember.getName() + "," + crewMember.getRank());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
