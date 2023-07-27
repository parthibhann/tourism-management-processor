package com.skill.tracker.processor.mapper;

import com.skill.profile.api.jaxb.types.Skill;
import com.skill.profile.api.jaxb.types.SkillProfileType;
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
public class SkillProfileCsvTypeMapperImpl implements SkillProfileCsvTypeMapper {

    private final DatatypeFactory datatypeFactory;

    public SkillProfileCsvTypeMapperImpl() {
        try {
            datatypeFactory = DatatypeFactory.newInstance();
        }
        catch ( DatatypeConfigurationException ex ) {
            throw new RuntimeException( ex );
        }
    }

    @Override
    public SkillProfileType skillProfileCsvModelToSkillProfileType(SkillProfileCsvModel skillProfileCsvModel) {
        if ( skillProfileCsvModel == null ) {
            return null;
        }

        SkillProfileType skillProfileType = new SkillProfileType();

        if ( skillProfileType.getSkillLevals() != null ) {
            List<Skill> list = skillLevals( skillProfileCsvModel.getSkillLevals() );
            if ( list != null ) {
                skillProfileType.getSkillLevals().addAll( list );
            }
        }
        if ( skillProfileType.getNonTechnicalSkillLevals() != null ) {
            List<Skill> list1 = nonTechnicalSkillLevals( skillProfileCsvModel.getNonTechnicalSkillLevals() );
            if ( list1 != null ) {
                skillProfileType.getNonTechnicalSkillLevals().addAll( list1 );
            }
        }
        skillProfileType.setUpdateTime( stringToXmlGregorianCalendar( skillProfileCsvModel.getUpdateTime(), "yyyy-MM-dd'T'HH:mm:ss'Z'" ) );
        skillProfileType.setId( skillProfileCsvModel.getId() );
        skillProfileType.setName( skillProfileCsvModel.getName() );
        skillProfileType.setAssociateId( skillProfileCsvModel.getAssociateId() );
        skillProfileType.setMobile( skillProfileCsvModel.getMobile() );
        skillProfileType.setEmail( skillProfileCsvModel.getEmail() );
        skillProfileType.setUpdatedBy( skillProfileCsvModel.getUpdatedBy() );

        return skillProfileType;
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
