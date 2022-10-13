package org.kainos.ea.validator;

import org.kainos.ea.exception.*;
import org.kainos.ea.models.JobRoleRequest;

public class JobRoleRequestValidator {

    public boolean isValidJobRole( JobRoleRequest jobRoleRequest ) throws JobRoleTitleEmptyException, BandLevelInvalidException, CapabilityInvalidException, JobFamilyInvalidException, JobRoleLinkInvalidException {

        String urlRegex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

        if ( jobRoleRequest.getTitle().length() < 1 ) {

            throw new JobRoleTitleEmptyException();
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

        return true;
    }
}
