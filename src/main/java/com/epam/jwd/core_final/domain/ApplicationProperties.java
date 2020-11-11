package com.epam.jwd.core_final.domain;

/**
 * This class should be IMMUTABLE!
 * <p>
 * Expected fields:
 * <p>
 * inputRootDir {@link String} - base dir for all input files
 * outputRootDir {@link String} - base dir for all output files
 * crewFileName {@link String}
 * missionsFileName {@link String}
 * spaceshipsFileName {@link String}
 * <p>
 * fileRefreshRate {@link Integer}
 * dateTimeFormat {@link String} - date/time format for {@link java.time.format.DateTimeFormatter} pattern
 */
public final class ApplicationProperties {
    private String inputRootDir;
    private String outputRootDir;
    private String crewFileName;
    private String missionsFileName;
    private String spaceshipsFileName;
    private Integer fileRefreshRate;
    private String dateTimeFormat;

    private ApplicationProperties(final String inputRootDir, final String outputRootDir, final String crewFileName,
                                 final String missionsFileName, final String spaceshipsFileName,
                                 final Integer fileRefreshRate, final String dateTimeFormat) {
        this.inputRootDir = inputRootDir;
        this.outputRootDir = outputRootDir;
        this.missionsFileName = missionsFileName;
        this.spaceshipsFileName = spaceshipsFileName;
        this.crewFileName = crewFileName;
        this.fileRefreshRate = fileRefreshRate;
        this.dateTimeFormat = dateTimeFormat;
    }

    public String getInputRootDir() {
        return inputRootDir;
    }

    public String getOutputRootDir() {
        return outputRootDir;
    }

    public String getCrewFileName() {
        return crewFileName;
    }

    public String getMissionsFileName() {
        return missionsFileName;
    }

    public String getSpaceshipsFileName() {
        return spaceshipsFileName;
    }

    public Integer getFileRefreshRate() {
        return fileRefreshRate;
    }

    public String getDateTimeFormat() {
        return dateTimeFormat;
    }

    public static ApplicationPropertiesBuilder builder() {
        return new ApplicationPropertiesBuilder();
    }

    public static final class ApplicationPropertiesBuilder {
        private String inputRootDir;
        private String outputRootDir;
        private String crewFileName;
        private String missionsFileName;
        private String spaceshipsFileName;
        private Integer fileRefreshRate;
        private String dateTimeFormat;

        public ApplicationPropertiesBuilder withInputRootDir(final String inputRootDir) {
            this.inputRootDir = inputRootDir;
            return this;
        }

        public ApplicationPropertiesBuilder withOutputRootDir(final String outputRootDir) {
            this.outputRootDir = outputRootDir;
            return this;
        }

        public ApplicationPropertiesBuilder withCrewFileName(final String crewFileName) {
            this.crewFileName = crewFileName;
            return this;
        }

        public ApplicationPropertiesBuilder withMissionsFileName(final String missionsFileName) {
            this.missionsFileName = missionsFileName;
            return this;
        }

        public ApplicationPropertiesBuilder withSpaceshipsFileName(final String spaceshipsFileName) {
            this.spaceshipsFileName = spaceshipsFileName;
            return this;
        }

        public ApplicationPropertiesBuilder withFileRefreshRate(final Integer fileRefreshRate) {
            this.fileRefreshRate = fileRefreshRate;
            return this;
        }

        public ApplicationPropertiesBuilder withDateTimeFormat(String dateTimeFormat) {
            this.dateTimeFormat = dateTimeFormat;
            return this;
        }

        public ApplicationProperties build() {
            return new ApplicationProperties(inputRootDir, outputRootDir, crewFileName,
                    missionsFileName, spaceshipsFileName, fileRefreshRate, dateTimeFormat);
        }
    }
}
