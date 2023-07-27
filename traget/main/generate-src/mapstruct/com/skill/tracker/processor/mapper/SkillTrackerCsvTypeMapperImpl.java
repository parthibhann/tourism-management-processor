package com.skill.tracker.processor.mapper;

import com.skill.tracker.api.jaxb.types.Skill;
import com.skill.tracker.api.jaxb.types.SkillTrackerRequest;
import com.skill.tracker.api.jaxb.types.SkillTrackerType;
import com.skill.tracker.processor.model.SkillProfileCsvModel;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class SkillTrackerCsvTypeMapperImpl implements SkillTrackerCsvTypeMapper {

    private final DatatypeFactory datatypeFactory;

    public SkillTrackerCsvTypeMapperImpl() {
        try {
            datatypeFactory = DatatypeFactory.newInstance();
        }
        catch ( DatatypeConfigurationException ex ) {
            throw new RuntimeException( ex );
        }
    }

    @Override
    public SkillTrackerType skillProfileCsvModelToSkillTrackerType(SkillProfileCsvModel skillProfileCsvModel) {
        if ( skillProfileCsvModel == null ) {
            return null;
        }

        SkillTrackerType skillTrackerType = new SkillTrackerType();

        if ( skillTrackerType.getSkillLevals() != null ) {
            List<Skill> list = skillLevals( skillProfileCsvModel.getSkillLevals() );
            if ( list != null ) {
                skillTrackerType.getSkillLevals().addAll( list );
            }
        }
        if ( skillTrackerType.getNonTechnicalSkillLevals() != null ) {
            List<Skill> list1 = nonTechnicalSkillLevals( skillProfileCsvModel.getNonTechnicalSkillLevals() );
            if ( list1 != null ) {
                skillTrackerType.getNonTechnicalSkillLevals().addAll( list1 );
            }
        }
        skillTrackerType.setUpdateTime( stringToXmlGregorianCalendar( skillProfileCsvModel.getUpdateTime(), "yyyy-MM-dd'T'HH:mm:ss'Z'" ) );
        skillTrackerType.setId( skillProfileCsvModel.getId() );
        skillTrackerType.setName( skillProfileCsvModel.getName() );
        skillTrackerType.setAssociateId( skillProfileCsvModel.getAssociateId() );
        skillTrackerType.setMobile( skillProfileCsvModel.getMobile() );
        skillTrackerType.setEmail( skillProfileCsvModel.getEmail() );
        skillTrackerType.setUpdatedBy( skillProfileCsvModel.getUpdatedBy() );

        return skillTrackerType;
    }

    @Override
    public Skill mapSkill(String skill, String level) {
        if ( skill == null && level == null ) {
            return null;
        }

        Skill skill1 = new Skill();

        skill1.setSkill( skill );
        if ( level != null ) {
            skill1.setLevel( Integer.parseInt( level ) );
        }

        return skill1;
    }

    @Override
    public SkillTrackerRequest skillTrackerTypeToSkillTrackerRequest(SkillTrackerType skillTrackerType) {
        if ( skillTrackerType == null ) {
            return null;
        }

        SkillTrackerRequest skillTrackerRequest = new SkillTrackerRequest();

        skillTrackerRequest.setSkillTracker( skillTrackerType );

        return skillTrackerRequest;
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
}
