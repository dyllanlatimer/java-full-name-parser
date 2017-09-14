import fullNameParser.FullNameParser;
import fullNameParser.ParsedName;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

public class FullNameParserTest
{
    @Test
    public void parseFullName_firstLastTest()
    {
        FullNameParser parser = new FullNameParser();
        String firstName = "Keanu";
        String lastName = "Reeves";
        String fullName = String.format("%s %s", firstName, lastName);

        ParsedName name = parser.parse(fullName);

        assertEquals("first name valid", firstName, name.getFirstName());
        assertEquals("last name valid", lastName, name.getLastName());
    }

    @Test
    public void parseFullName_middleNameTest()
    {
        FullNameParser parser = new FullNameParser();
        String firstName = "Tommy";
        String middleName = "Lee";
        String lastName = "Jones";
        String fullName = String.format("%s %s %s", firstName, middleName, lastName);

        ParsedName name = parser.parse(fullName);

        assertEquals("first name valid", firstName, name.getFirstName());
        assertThat("middle name valid", name.getMiddleNames(), contains(middleName));
        assertEquals("last name valid", lastName, name.getLastName());
    }

    @Test
    public void parseFullName_titleTest()
    {
        FullNameParser parser = new FullNameParser();
        String title = "Captain";
        String firstName = "Jack";
        String lastName = "Sparrow";
        String fullName = String.format("%s %s %s", title, firstName, lastName);

        ParsedName name = parser.parse(fullName);

        assertThat("title valid", name.getTitles(), contains(title));
        assertEquals("first name valid", firstName, name.getFirstName());
        assertEquals("last name valid", lastName, name.getLastName());
    }

    @Test
    public void parseFullName_prefixTest()
    {
        FullNameParser parser = new FullNameParser();
        String firstName = "Victor";
        String lastName = "Von Doom";
        String fullName = String.format("%s %s", firstName, lastName);

        ParsedName name = parser.parse(fullName);

        assertEquals("first name valid", firstName, name.getFirstName());
        assertEquals("last name valid", lastName, name.getLastName());
    }

    @Test
    public void parseFullName_suffixTest()
    {
        FullNameParser parser = new FullNameParser();
        String firstName = "Dale";
        String lastName = "Earnhardt";
        String suffix = "Sr";
        String fullName = String.format("%s %s %s", firstName, lastName, suffix);

        ParsedName name = parser.parse(fullName);

        assertEquals("first name valid", firstName, name.getFirstName());
        assertEquals("last name valid", lastName, name.getLastName());
        assertThat("suffix valid", name.getSuffixes(), contains(suffix));
    }

    @Test
    public void parseFullName_suffixTest_Decimal()
    {
        FullNameParser parser = new FullNameParser();
        String firstName = "Dale";
        String lastName = "Earnhardt";
        String suffix = "Sr.";
        String fullName = String.format("%s %s %s", firstName, lastName, suffix);

        ParsedName name = parser.parse(fullName);

        assertEquals("first name valid", firstName, name.getFirstName());
        assertEquals("last name valid", lastName, name.getLastName());
        assertThat("suffix valid", name.getSuffixes(), contains(suffix));
    }

    @Test
    public void parseFullName_nicknameTest()
    {
        FullNameParser parser = new FullNameParser();
        String firstName = "Dwayne";
        String nickname = "The Rock";
        String lastName = "Johnson";
        String fullName = String.format("%s \"%s\" %s", firstName, nickname, lastName);

        ParsedName name = parser.parse(fullName);

        assertEquals("first name valid", firstName, name.getFirstName());
        assertThat("nickname valid", name.getNicknames(), contains(nickname));
        assertEquals("last name valid", lastName, name.getLastName());
    }

    @Test
    public void parsedName_getFullnameTest()
    {
        ParsedName parsedName = new ParsedName();
        parsedName.setTitles(Collections.singletonList("Mr."));
        parsedName.setFirstName("Bob");
        parsedName.setMiddleNames(Arrays.asList("Something", "OrOther"));
        parsedName.setNicknames(Arrays.asList("super", "awesome"));
        parsedName.setLastName("Smith");
        parsedName.setSuffixes(Collections.singletonList("III"));
        assertEquals("Full name valid", "Mr. Bob Something OrOther \"super\" \"awesome\" Smith III", parsedName.toString());
    }

    @Test
    public void parsedName_getFullnameTest_missingMiddleName()
    {
        ParsedName parsedName = new ParsedName();
        parsedName.setTitles(Collections.singletonList("Mr."));
        parsedName.setFirstName("Bob");
        parsedName.setNicknames(Arrays.asList("super", "awesome"));
        parsedName.setLastName("Smith");
        parsedName.setSuffixes(Collections.singletonList("III"));
        assertEquals("Full name valid", "Mr. Bob \"super\" \"awesome\" Smith III", parsedName.toString());
    }

    @Test
    public void parsedName_getFullnameTest_missingNickname()
    {
        ParsedName parsedName = new ParsedName();
        parsedName.setTitles(Collections.singletonList("Mr."));
        parsedName.setFirstName("Bob");
        parsedName.setMiddleNames(Arrays.asList("Something", "OrOther"));
        parsedName.setLastName("Smith");
        parsedName.setSuffixes(Collections.singletonList("III"));
        assertEquals("Full name valid", "Mr. Bob Something OrOther Smith III", parsedName.toString());
    }

    @Test
    public void parsedName_getFullnameTest_firstNameLastName()
    {
        ParsedName parsedName = new ParsedName();
        parsedName.setFirstName("Bob");
        parsedName.setLastName("Smith");
        assertEquals("Full name valid", "Bob Smith", parsedName.toString());
    }

    @Test
    public void parsedName_firstNameOnlyTest()
    {
        FullNameParser parser = new FullNameParser();
        String firstName = "Bob";

        ParsedName name = parser.parse(firstName);

        assertEquals("first name valid", firstName, name.getFirstName());
    }
}