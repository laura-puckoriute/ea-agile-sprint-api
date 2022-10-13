package org.kainos.ea.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.kainos.ea.exception.*;
import org.kainos.ea.models.JobRoleRequest;

public class JobRoleRequestValidatorTest {

    JobRoleRequestValidator jobRoleRequestValidator = new JobRoleRequestValidator();

    @Test
    public void isValidJobRole_shouldReturnTrue_whenValidJobRole() throws JobRoleTitleEmptyException, BandLevelInvalidException, CapabilityInvalidException, JobFamilyInvalidException, JobRoleLinkInvalidException, FieldNameTooLongException {

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
    public void isValidJobRole_shouldReturnTrue_whenValidJobRole_withValidLink() throws JobRoleTitleEmptyException, BandLevelInvalidException, CapabilityInvalidException, JobFamilyInvalidException, JobRoleLinkInvalidException, FieldNameTooLongException {

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
    public void isValidJobRole_shouldThrowFieldNameTooLongException_whenTitleTooLong() throws JobRoleTitleEmptyException, BandLevelInvalidException, CapabilityInvalidException, JobFamilyInvalidException, JobRoleLinkInvalidException {

        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Software EngineerSoftware EngineerSoftware EngineerSoftware EngineerSoftware EngineerSoftware EngineerSoftware EngineerSoftware EngineerSoftware EngineerSoftware EngineerSoftware EngineerSoftware EngineerSoftware EngineerSoftware EngineerSoftware EngineerSoftware Engineer",
                "",
                "",
                "",
                1,
                1,
                1
        );

        Assertions.assertThrows( FieldNameTooLongException.class,
                () -> jobRoleRequestValidator.isValidJobRole( jobRoleRequest ));
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

    @Test
    public void isValidJobRole_shouldReturnThrowFieldNameTooLongException_whenDescriptionTooLong() throws JobRoleTitleEmptyException, BandLevelInvalidException, CapabilityInvalidException, JobFamilyInvalidException, JobRoleLinkInvalidException, FieldNameTooLongException {

        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Software Engineer",
                "•Experience delivering software designs for multi-tiered modern software applications.\\n•Experience of technical ownership for a product/software project, including architecture, estimation, product planning and user story/requirement creation.\\n•Understands non-functional concerns for customers and has experience incorporating these into the application design.\\n•Has experience with public cloud platforms, such as AWS and Azure, including SaaS and PaaS offerings.\\n•Able to simply and clearly communicate technical design in conversation, documentation and presentations.\\n•Able to make effective decisions within fast-moving delivery.\\n•We are passionate about developing people – a demonstrated ability in managing, coaching and developing junior members of your team and wider community.•Experience delivering software designs for multi-tiered modern software applications.\\n•Experience of technical ownership for a product/software project, including architecture, estimation, product planning and user story/requirement creation.\\n•Understands non-functional concerns for customers and has experience incorporating these into the application design.\\n•Has experience with public cloud platforms, such as AWS and Azure, including SaaS and PaaS offerings.\\n•Able to simply and clearly communicate technical design in conversation, documentation and presentations.\\n•Able to make effective decisions within fast-moving delivery.\\n•We are passionate about developing people – a demonstrated ability in managing, coaching and developing junior members of your team and wider community.•Experience delivering software designs for multi-tiered modern software applications.\\n•Experience of technical ownership for a product/software project, including architecture, estimation, product planning and user story/requirement creation.\\n•Understands non-functional concerns for customers and has experience incorporating these into the application design.\\n•Has experience with public cloud platforms, such as AWS and Azure, including SaaS and PaaS offerings.\\n•Able to simply and clearly communicate technical design in conversation, documentation and presentations.\\n•Able to make effective decisions within fast-moving delivery.\\n•We are passionate about developing people – a demonstrated ability in managing, coaching and developing junior members of your team and wider community.•Experience delivering software designs for multi-tiered modern software applications.\\n•Experience of technical ownership for a product/software project, including architecture, estimation, product planning and user story/requirement creation.\\n•Understands non-functional concerns for customers and has experience incorporating these into the application design.\\n•Has experience with public cloud platforms, such as AWS and Azure, including SaaS and PaaS offerings.\\n•Able to simply and clearly communicate technical design in conversation, documentation and presentations.\\n•Able to make effective decisions within fast-moving delivery.\\n•We are passionate about developing people – a demonstrated ability in managing, coaching and developing junior members of your team and wider community.•Experience delivering software designs for multi-tiered modern software applications.\\n•Experience of technical ownership for a product/software project, including architecture, estimation, product planning and user story/requirement creation.\\n•Understands non-functional concerns for customers and has experience incorporating these into the application design.\\n•Has experience with public cloud platforms, such as AWS and Azure, including SaaS and PaaS offerings.\\n•Able to simply and clearly communicate technical design in conversation, documentation and presentations.\\n•Able to make effective decisions within fast-moving delivery.\\n•We are passionate about developing people – a demonstrated ability in managing, coaching and developing junior members of your team and wider community.•Experience delivering software designs for multi-tiered modern software applications.\\n•Experience of technical ownership for a product/software project, including architecture, estimation, product planning and user story/requirement creation.\\n•Understands non-functional concerns for customers and has experience incorporating these into the application design.\\n•Has experience with public cloud platforms, such as AWS and Azure, including SaaS and PaaS offerings.\\n•Able to simply and clearly communicate technical design in conversation, documentation and presentations.\\n•Able to make effective decisions within fast-moving delivery.\\n•We are passionate about developing people – a demonstrated ability in managing, coaching and developing junior members of your team and wider community.•Experience delivering software designs for multi-tiered modern software applications.\\n•Experience of technical ownership for a product/software project, including architecture, estimation, product planning and user story/requirement creation.\\n•Understands non-functional concerns for customers and has experience incorporating these into the application design.\\n•Has experience with public cloud platforms, such as AWS and Azure, including SaaS and PaaS offerings.\\n•Able to simply and clearly communicate technical design in conversation, documentation and presentations.\\n•Able to make effective decisions within fast-moving delivery.\\n•We are passionate about developing people – a demonstrated ability in managing, coaching and developing junior members of your team and wider community.•Experience delivering software designs for multi-tiered modern software applications.\\n•Experience of technical ownership for a product/software project, including architecture, estimation, product planning and user story/requirement creation.\\n•Understands non-functional concerns for customers and has experience incorporating these into the application design.\\n•Has experience with public cloud platforms, such as AWS and Azure, including SaaS and PaaS offerings.\\n•Able to simply and clearly communicate technical design in conversation, documentation and presentations.\\n•Able to make effective decisions within fast-moving delivery.\\n•We are passionate about developing people – a demonstrated ability in managing, coaching and developing junior members of your team and wider community.•Experience delivering software designs for multi-tiered modern software applications.\\n•Experience of technical ownership for a product/software project, including architecture, estimation, product planning and user story/requirement creation.\\n•Understands non-functional concerns for customers and has experience incorporating these into the application design.\\n•Has experience with public cloud platforms, such as AWS and Azure, including SaaS and PaaS offerings.\\n•Able to simply and clearly communicate technical design in conversation, documentation and presentations.\\n•Able to make effective decisions within fast-moving delivery.\\n•We are passionate about developing people – a demonstrated ability in managing, coaching and developing junior members of your team and wider community.•Experience delivering software designs for multi-tiered modern software applications.\\n•Experience of technical ownership for a product/software project, including architecture, estimation, product planning and user story/requirement creation.\\n•Understands non-functional concerns for customers and has experience incorporating these into the application design.\\n•Has experience with public cloud platforms, such as AWS and Azure, including SaaS and PaaS offerings.\\n•Able to simply and clearly communicate technical design in conversation, documentation and presentations.\\n•Able to make effective decisions within fast-moving delivery.\\n•We are passionate about developing people – a demonstrated ability in managing, coaching and developing junior members of your team and wider community.",
                "",
                "",
                1,
                1,
                1
        );

        Assertions.assertThrows( FieldNameTooLongException.class,
                () -> jobRoleRequestValidator.isValidJobRole( jobRoleRequest ));
    }

    @Test
    public void isValidJobRole_shouldReturnThrowFieldNameTooLongException_whenResponsibilitiesTooLong() throws JobRoleTitleEmptyException, BandLevelInvalidException, CapabilityInvalidException, JobFamilyInvalidException, JobRoleLinkInvalidException, FieldNameTooLongException {

        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Software Engineer",
                "",
                "•Experience delivering software designs for multi-tiered modern software applications.\\n•Experience of technical ownership for a product/software project, including architecture, estimation, product planning and user story/requirement creation.\\n•Understands non-functional concerns for customers and has experience incorporating these into the application design.\\n•Has experience with public cloud platforms, such as AWS and Azure, including SaaS and PaaS offerings.\\n•Able to simply and clearly communicate technical design in conversation, documentation and presentations.\\n•Able to make effective decisions within fast-moving delivery.\\n•We are passionate about developing people – a demonstrated ability in managing, coaching and developing junior members of your team and wider community.•Experience delivering software designs for multi-tiered modern software applications.\\n•Experience of technical ownership for a product/software project, including architecture, estimation, product planning and user story/requirement creation.\\n•Understands non-functional concerns for customers and has experience incorporating these into the application design.\\n•Has experience with public cloud platforms, such as AWS and Azure, including SaaS and PaaS offerings.\\n•Able to simply and clearly communicate technical design in conversation, documentation and presentations.\\n•Able to make effective decisions within fast-moving delivery.\\n•We are passionate about developing people – a demonstrated ability in managing, coaching and developing junior members of your team and wider community.•Experience delivering software designs for multi-tiered modern software applications.\\n•Experience of technical ownership for a product/software project, including architecture, estimation, product planning and user story/requirement creation.\\n•Understands non-functional concerns for customers and has experience incorporating these into the application design.\\n•Has experience with public cloud platforms, such as AWS and Azure, including SaaS and PaaS offerings.\\n•Able to simply and clearly communicate technical design in conversation, documentation and presentations.\\n•Able to make effective decisions within fast-moving delivery.\\n•We are passionate about developing people – a demonstrated ability in managing, coaching and developing junior members of your team and wider community.•Experience delivering software designs for multi-tiered modern software applications.\\n•Experience of technical ownership for a product/software project, including architecture, estimation, product planning and user story/requirement creation.\\n•Understands non-functional concerns for customers and has experience incorporating these into the application design.\\n•Has experience with public cloud platforms, such as AWS and Azure, including SaaS and PaaS offerings.\\n•Able to simply and clearly communicate technical design in conversation, documentation and presentations.\\n•Able to make effective decisions within fast-moving delivery.\\n•We are passionate about developing people – a demonstrated ability in managing, coaching and developing junior members of your team and wider community.•Experience delivering software designs for multi-tiered modern software applications.\\n•Experience of technical ownership for a product/software project, including architecture, estimation, product planning and user story/requirement creation.\\n•Understands non-functional concerns for customers and has experience incorporating these into the application design.\\n•Has experience with public cloud platforms, such as AWS and Azure, including SaaS and PaaS offerings.\\n•Able to simply and clearly communicate technical design in conversation, documentation and presentations.\\n•Able to make effective decisions within fast-moving delivery.\\n•We are passionate about developing people – a demonstrated ability in managing, coaching and developing junior members of your team and wider community.•Experience delivering software designs for multi-tiered modern software applications.\\n•Experience of technical ownership for a product/software project, including architecture, estimation, product planning and user story/requirement creation.\\n•Understands non-functional concerns for customers and has experience incorporating these into the application design.\\n•Has experience with public cloud platforms, such as AWS and Azure, including SaaS and PaaS offerings.\\n•Able to simply and clearly communicate technical design in conversation, documentation and presentations.\\n•Able to make effective decisions within fast-moving delivery.\\n•We are passionate about developing people – a demonstrated ability in managing, coaching and developing junior members of your team and wider community.•Experience delivering software designs for multi-tiered modern software applications.\\n•Experience of technical ownership for a product/software project, including architecture, estimation, product planning and user story/requirement creation.\\n•Understands non-functional concerns for customers and has experience incorporating these into the application design.\\n•Has experience with public cloud platforms, such as AWS and Azure, including SaaS and PaaS offerings.\\n•Able to simply and clearly communicate technical design in conversation, documentation and presentations.\\n•Able to make effective decisions within fast-moving delivery.\\n•We are passionate about developing people – a demonstrated ability in managing, coaching and developing junior members of your team and wider community.•Experience delivering software designs for multi-tiered modern software applications.\\n•Experience of technical ownership for a product/software project, including architecture, estimation, product planning and user story/requirement creation.\\n•Understands non-functional concerns for customers and has experience incorporating these into the application design.\\n•Has experience with public cloud platforms, such as AWS and Azure, including SaaS and PaaS offerings.\\n•Able to simply and clearly communicate technical design in conversation, documentation and presentations.\\n•Able to make effective decisions within fast-moving delivery.\\n•We are passionate about developing people – a demonstrated ability in managing, coaching and developing junior members of your team and wider community.•Experience delivering software designs for multi-tiered modern software applications.\\n•Experience of technical ownership for a product/software project, including architecture, estimation, product planning and user story/requirement creation.\\n•Understands non-functional concerns for customers and has experience incorporating these into the application design.\\n•Has experience with public cloud platforms, such as AWS and Azure, including SaaS and PaaS offerings.\\n•Able to simply and clearly communicate technical design in conversation, documentation and presentations.\\n•Able to make effective decisions within fast-moving delivery.\\n•We are passionate about developing people – a demonstrated ability in managing, coaching and developing junior members of your team and wider community.•Experience delivering software designs for multi-tiered modern software applications.\\n•Experience of technical ownership for a product/software project, including architecture, estimation, product planning and user story/requirement creation.\\n•Understands non-functional concerns for customers and has experience incorporating these into the application design.\\n•Has experience with public cloud platforms, such as AWS and Azure, including SaaS and PaaS offerings.\\n•Able to simply and clearly communicate technical design in conversation, documentation and presentations.\\n•Able to make effective decisions within fast-moving delivery.\\n•We are passionate about developing people – a demonstrated ability in managing, coaching and developing junior members of your team and wider community.",
                "",
                1,
                1,
                1
        );

        Assertions.assertThrows( FieldNameTooLongException.class,
                () -> jobRoleRequestValidator.isValidJobRole( jobRoleRequest ));
    }

    @Test
    public void isValidJobRole_shouldThrowFieldNameTooLongException_whenLinkTooLong() throws JobRoleTitleEmptyException, BandLevelInvalidException, CapabilityInvalidException, JobFamilyInvalidException, JobRoleLinkInvalidException, FieldNameTooLongException {

        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Software Engineer",
                "",
                "",
                "https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FEngineering%2FJob%20Profile%20%2D%20Technical%20Architect%20%28Consultant%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FEngineering&p=true&ga=1/kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FEngineering%2FJob%20Profile%20%2D%20Technical%20Architect%20%28Consultant%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FEngineering&p=true&ga=1/kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FEngineering%2FJob%20Profile%20%2D%20Technical%20Architect%20%28Consultant%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FEngineering&p=true&ga=1/kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FEngineering%2FJob%20Profile%20%2D%20Technical%20Architect%20%28Consultant%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FEngineering&p=true&ga=1/kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FEngineering%2FJob%20Profile%20%2D%20Technical%20Architect%20%28Consultant%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FEngineering&p=true&ga=1",
                1,
                1,
                1
        );

        Assertions.assertThrows( FieldNameTooLongException.class,
                () -> jobRoleRequestValidator.isValidJobRole( jobRoleRequest ));
    }
}
