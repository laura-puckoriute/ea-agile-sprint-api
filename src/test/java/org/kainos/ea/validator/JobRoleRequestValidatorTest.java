package org.kainos.ea.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.kainos.ea.exception.*;
import org.kainos.ea.models.JobRoleRequest;

public class JobRoleRequestValidatorTest {

    JobRoleRequestValidator jobRoleRequestValidator = new JobRoleRequestValidator();

    @Test
    public void isValidJobRole_shouldReturnTrue_whenValidJobRole() throws JobRoleTitleEmptyException, BandLevelInvalidException, CapabilityInvalidException, JobFamilyInvalidException, JobRoleLinkInvalidException {

        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Software Engineer",
                "",
                "",
                "",
                1,
                1,
                1
        );

        Assertions.assertTrue( jobRoleRequestValidator.isValidJobRole( jobRoleRequest ));
    }

    @Test
    public void isValidJobRole_shouldReturnTrue_whenValidJobRole_withValidLink() throws JobRoleTitleEmptyException, BandLevelInvalidException, CapabilityInvalidException, JobFamilyInvalidException, JobRoleLinkInvalidException {

        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Software Engineer",
                "",
                "",
                "https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FEngineering%2FJob%20Profile%20%2D%20Technical%20Architect%20%28Consultant%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FEngineering&p=true&ga=1",
                1,
                1,
                1
        );

        Assertions.assertTrue( jobRoleRequestValidator.isValidJobRole( jobRoleRequest ));
    }

    @Test
    public void isValidJobRole_shouldThrowJobRoleTitleEmptyException_whenTitleEmpty() throws JobRoleTitleEmptyException, BandLevelInvalidException, CapabilityInvalidException, JobFamilyInvalidException, JobRoleLinkInvalidException {

        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "",
                "",
                "",
                "",
                1,
                1,
                1
        );

        Assertions.assertThrows( JobRoleTitleEmptyException.class,
                () -> jobRoleRequestValidator.isValidJobRole( jobRoleRequest ));
    }

    @Test
    public void isValidJobRole_shouldThrowBandLevelInvalidException_whenBandLevelIDInvalid() throws JobRoleTitleEmptyException, BandLevelInvalidException, CapabilityInvalidException, JobFamilyInvalidException, JobRoleLinkInvalidException {

        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Software Engineer",
                "",
                "",
                "",
                -1,
                1,
                1
        );

        Assertions.assertThrows( BandLevelInvalidException.class,
                () -> jobRoleRequestValidator.isValidJobRole( jobRoleRequest ));
    }

    @Test
    public void isValidJobRole_shouldThrowCapabilityInvalidException_whenCapabilityIDInvalid() throws JobRoleTitleEmptyException, BandLevelInvalidException, CapabilityInvalidException, JobFamilyInvalidException, JobRoleLinkInvalidException {

        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Software Engineer",
                "",
                "",
                "",
                1,
                -1,
                1
        );

        Assertions.assertThrows( CapabilityInvalidException.class,
                () -> jobRoleRequestValidator.isValidJobRole( jobRoleRequest ));
    }

    @Test
    public void isValidJobRole_shouldThrowJobFamilyInvalidException_whenJobFamilyIDInvalid() throws JobRoleTitleEmptyException, BandLevelInvalidException, CapabilityInvalidException, JobFamilyInvalidException, JobRoleLinkInvalidException {

        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Software Engineer",
                "",
                "",
                "",
                1,
                1,
                -1
        );

        Assertions.assertThrows( JobFamilyInvalidException.class,
                () -> jobRoleRequestValidator.isValidJobRole( jobRoleRequest ));
    }

    @Test
    public void isValidJobRole_shouldThrowJobRoleLinkInvalidException_whenInvalidLink() throws JobRoleTitleEmptyException, BandLevelInvalidException, CapabilityInvalidException, JobFamilyInvalidException, JobRoleLinkInvalidException {

        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Software Engineer",
                "",
                "",
                "thisisnotalink",
                1,
                1,
                1
        );

        Assertions.assertThrows( JobRoleLinkInvalidException.class,
                () -> jobRoleRequestValidator.isValidJobRole( jobRoleRequest ));
    }
}
