package com.skill.tracker.processor.mapper;

import com.skill.tracker.api.jaxb.types.Messages;
import com.skill.tracker.api.jaxb.types.Pageable;
import com.skill.tracker.api.jaxb.types.Response;
import com.skill.tracker.api.jaxb.types.Skill;
import com.skill.tracker.api.jaxb.types.SkillTrackerResponse;
import com.skill.tracker.api.jaxb.types.SkillTrackerType;
import com.skill.tracker.processor.model.SkillAdmin;
import com.skill.tracker.processor.model.SkillLevel;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.processing.Generated;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class SkillTrackerAdminMapperImpl implements SkillTrackerAdminMapper {

    private final DatatypeFactory datatypeFactory;

    public SkillTrackerAdminMapperImpl() {
        try {
            datatypeFactory = DatatypeFactory.newInstance();
        }
        catch ( DatatypeConfigurationException ex ) {
            throw new RuntimeException( ex );
        }
    }

    @Override
    public SkillAdmin skillTrackerTypeToSkillAdmin(SkillTrackerType skillTrackerType) {
        if ( skillTrackerType == null ) {
            return null;
        }

        SkillAdmin skillAdmin = new SkillAdmin();

        skillAdmin.setId( skillTrackerType.getId() );
        skillAdmin.setName( skillTrackerType.getName() );
        skillAdmin.setAssociateId( skillTrackerType.getAssociateId() );
        skillAdmin.setMobile( skillTrackerType.getMobile() );
        skillAdmin.setEmail( skillTrackerType.getEmail() );
        skillAdmin.setSkillLevals( skillListToSkillLevelList( skillTrackerType.getSkillLevals() ) );
        skillAdmin.setNonTechnicalSkillLevals( skillListToSkillLevelList( skillTrackerType.getNonTechnicalSkillLevals() ) );
        skillAdmin.setUpdateTime( xmlGregorianCalendarToString( skillTrackerType.getUpdateTime(), null ) );
        skillAdmin.setUpdatedBy( skillTrackerType.getUpdatedBy() );

        return skillAdmin;
    }

    @Override
    public List<SkillTrackerType> skillAdminToSkillTrackerTypes(List<SkillAdmin> skillAdmin) {
        if ( skillAdmin == null ) {
            return null;
        }

        List<SkillTrackerType> list = new ArrayList<SkillTrackerType>( skillAdmin.size() );
        for ( SkillAdmin skillAdmin1 : skillAdmin ) {
            list.add( skillAdminToSkillTrackerType( skillAdmin1 ) );
        }

        return list;
    }

    @Override
    public SkillTrackerResponse skillTrackerTypeToSkillTrackerResponse(List<SkillTrackerType> skillTrackers, Response response, Pageable pageable) {
        if ( skillTrackers == null && response == null && pageable == null ) {
            return null;
        }

        SkillTrackerResponse skillTrackerResponse = new SkillTrackerResponse();

        if ( skillTrackerResponse.getSkillTrackers() != null ) {
            List<SkillTrackerType> list = skillTrackers;
            if ( list != null ) {
                skillTrackerResponse.getSkillTrackers().addAll( list );
            }
        }
        skillTrackerResponse.setResponse( response );
        skillTrackerResponse.setPageable( pageable );

        return skillTrackerResponse;
    }

    @Override
    public Messages createMessages(String type, String message) {
        if ( type == null && message == null ) {
            return null;
        }

        Messages messages = new Messages();

        messages.setType( type );
        messages.setMessage( message );

        return messages;
    }

    @Override
    public Response createResponse(String status, String satusText, List<Messages> messages) {
        if ( status == null && satusText == null && messages == null ) {
            return null;
        }

        Response response = new Response();

        response.setStatus( status );
        response.setSatusText( satusText );
        if ( response.getMessages() != null ) {
            List<Messages> list = messages;
            if ( list != null ) {
                response.getMessages().addAll( list );
            }
        }

        return response;
    }

    private String xmlGregorianCalendarToString( XMLGregorianCalendar xcal, String dateFormat ) {
        if ( xcal == null ) {
            return null;
        }

        if (dateFormat == null ) {
            return xcal.toString();
        }
        else {
            Date d = xcal.toGregorianCalendar().getTime();
            SimpleDateFormat sdf = new SimpleDateFormat( dateFormat );
            return sdf.format( d );
        }
    }

    private XMLGregorianCalendar stringToXmlGregorianCalendar( String date, String dateFormat ) {
        if ( date == null ) {
            return null;
        }

        try {
            if ( dateFormat != null ) {
                DateFormat df = new SimpleDateFormat( dateFormat );
                GregorianCalendar c = new GregorianCalendar();
                c.setTime( df.parse( date ) );
                return datatypeFactory.newXMLGregorianCalendar( c );
            }
            else {
                return datatypeFactory.newXMLGregorianCalendar( date );
            }
        }
        catch ( ParseException ex ) {
            throw new RuntimeException( ex );
        }
    }

    protected SkillLevel skillToSkillLevel(Skill skill) {
        if ( skill == null ) {
            return null;
        }

        SkillLevel skillLevel = new SkillLevel();

        skillLevel.setSkill( skill.getSkill() );
        if ( skill.getLevel() != null ) {
            skillLevel.setLevel( skill.getLevel() );
        }

        return skillLevel;
    }

    protected List<SkillLevel> skillListToSkillLevelList(List<Skill> list) {
        if ( list == null ) {
            return null;
        }

        List<SkillLevel> list1 = new ArrayList<SkillLevel>( list.size() );
        for ( Skill skill : list ) {
            list1.add( skillToSkillLevel( skill ) );
        }

        return list1;
    }

    protected Skill skillLevelToSkill(SkillLevel skillLevel) {
        if ( skillLevel == null ) {
            return null;
        }

        Skill skill = new Skill();

        skill.setSkill( skillLevel.getSkill() );
        skill.setLevel( skillLevel.getLevel() );

        return skill;
    }

    protected List<Skill> skillLevelListToSkillList(List<SkillLevel> list) {
        if ( list == null ) {
            return null;
        }

        List<Skill> list1 = new ArrayList<Skill>( list.size() );
        for ( SkillLevel skillLevel : list ) {
            list1.add( skillLevelToSkill( skillLevel ) );
        }

        return list1;
    }

    protected SkillTrackerType skillAdminToSkillTrackerType(SkillAdmin skillAdmin) {
        if ( skillAdmin == null ) {
            return null;
        }

        SkillTrackerType skillTrackerType = new SkillTrackerType();

        skillTrackerType.setId( skillAdmin.getId() );
        skillTrackerType.setName( skillAdmin.getName() );
        skillTrackerType.setAssociateId( skillAdmin.getAssociateId() );
        skillTrackerType.setMobile( skillAdmin.getMobile() );
        skillTrackerType.setEmail( skillAdmin.getEmail() );
        skillTrackerType.setUpdateTime( stringToXmlGregorianCalendar( skillAdmin.getUpdateTime(), null ) );
        skillTrackerType.setUpdatedBy( skillAdmin.getUpdatedBy() );
        if ( skillTrackerType.getSkillLevals() != null ) {
            List<Skill> list = skillLevelListToSkillList( skillAdmin.getSkillLevals() );
            if ( list != null ) {
                skillTrackerType.getSkillLevals().addAll( list );
            }
        }
        if ( skillTrackerType.getNonTechnicalSkillLevals() != null ) {
            List<Skill> list1 = skillLevelListToSkillList( skillAdmin.getNonTechnicalSkillLevals() );
            if ( list1 != null ) {
                skillTrackerType.getNonTechnicalSkillLevals().addAll( list1 );
            }
        }

        return skillTrackerType;
    }
}
