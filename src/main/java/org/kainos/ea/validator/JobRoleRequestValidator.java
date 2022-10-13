package org.kainos.ea.validator;

import org.kainos.ea.exception.*;
import org.kainos.ea.models.JobRoleRequest;

public class JobRoleRequestValidator {

    public boolean isValidJobRole( JobRoleRequest jobRoleRequest ) throws JobRoleTitleEmptyException, BandLevelInvalidException, CapabilityInvalidException, JobFamilyInvalidException, JobRoleLinkInvalidException, FieldNameTooLongException {

        String urlRegex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

        if ( jobRoleRequest.getTitle().length() < 1 ) {

            throw new JobRoleTitleEmptyException();
        }

        if ( jobRoleRequest.getTitle().length() > 50 ) {

            throw new FieldNameTooLongException();
        }

        if ( jobRoleRequest.getRequirements().length() > 2048 ) {

            throw new FieldNameTooLongException();
        }

        if ( jobRoleRequest.getResponsibilities().length() > 2048 ) {

            throw new FieldNameTooLongException();
        }

        if ( jobRoleRequest.getBandLevelID() < 1 ) {

            throw new BandLevelInvalidException();
        }

        if ( jobRoleRequest.getCapabilityID() < 1 ) {

            throw new CapabilityInvalidException();
        }

        if ( jobRoleRequest.getJobFamilyID() < 1 ) {

            throw new JobFamilyInvalidException();
        }

        if ( jobRoleRequest.getLink().length() > 0 ) {

            if ( !jobRoleRequest.getLink().matches( urlRegex ) ) throw new JobRoleLinkInvalidException();
        }

        if ( jobRoleRequest.getLink().length() > 512 ) {

            throw new FieldNameTooLongException();
        }

        return true;
    }
}
