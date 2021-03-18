package com.epam.jwd.core_final.writer.impl;

import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.writer.BaseEntityWriter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CrewMemberWriter implements BaseEntityWriter<CrewMember> {
    private ApplicationProperties applicationProperties = new ApplicationProperties();
    private static CrewMemberWriter instance;

    private CrewMemberWriter() {
    }

    public static CrewMemberWriter getInstance() {
        if (instance == null) {
            instance = new CrewMemberWriter();
        }
        return instance;
    }

    @Override
    public void writeOneEntityToDatabase(CrewMember crewMember) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(getInputFileName(), true))) {
            bufferedWriter.write(crewMember.getRole().getId() + "," + crewMember.getName() + "," + crewMember.getRank().getId() + ";");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeAllToDatabase(List<String> crewMembers) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(getInputFileName(), true))) {
            for (String s : crewMembers) {
                bufferedWriter.write(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeToJsonFile(CrewMember baseEntity) {

    }

    private String getInputFileName() {
        String crewFileName = applicationProperties.getCrewFileName();
        String dirName = applicationProperties.getInputRootDir();
        String dir = applicationProperties.getRootDir();
        return dir + "/" + dirName + "/" + crewFileName;
    }
}
