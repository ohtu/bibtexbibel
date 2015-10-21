import org.junit.contrib.java.lang.system.SystemOutRule;
import com.miniprojekti.bibtexbible.*;
import com.miniprojekti.bibtexbible.io.StubIO;
import com.miniprojekti.bibtexbible.io.IO;
import com.miniprojekti.bibtexbible.ui.ConsoleUI;
import com.miniprojekti.bibtexbible.ui.UI;

description 'User can add reference and see it listed'

scenario "user can't list references when there is none", {
    given 'application is initiliazed correctly', {
        io = new StubIO("2", "0")
        rc = new ReferenceController()        
        ui = new ConsoleUI(io,rc)
        ui.run();
    }
    when 'correct commands are entered', {
        prints = io.getPrints()
    }
    then 'user can see see no references listed', {
        String print = prints.toString()
        print.contains("No references.").shouldBe(true)
    }
}

scenario "user can add reference", {
    given 'application is initiliazed correctly', {
        io = new StubIO("1", "1", "Kirjoittaja", "", "Titteli", "2015", "", "", "", "", "", "", "",
                        "2", "0")
        ui = new ConsoleUI(io)
        rc = new ReferenceController()        
        ui = new ConsoleUI(io,rc)
        ui.run();
    }
    when 'correct commands are entered', {
        prints = io.getPrints()
    }
    then 'user can see reference listed', {
        prints.toString().contains("Book")
        prints.toString().contains("author = Kirjoittaja")
        prints.toString().contains("title = Titteli")
        prints.toString().contains("year = 2015")
    }
}

scenario "user can't add duplicate reference", {
    given 'application is initialized correctly', {
        io = new StubIO("1", "1", "Kirjoittaja", "", "Titteli", "2015", "", "", "", "", "", "", "",
                        "1", "1", "Kirjoittaja", "", "Titteli", "2015", "", "", "", "", "", "", "",
                        "2", "0")
        ui = new ConsoleUI(io)
        rc = new ReferenceController()        
        ui = new ConsoleUI(io,rc)
        ui.run();
    }
    when 'correct commands are entered', {
        prints = io.getPrints()
    }
    then 'user can see see only ONE reference listed', {
        String print = prints.toString()
        String pattern = "@Book: Key = Kirj2015, author = Kirjoittaja, editor = , title = Titteli, year = 2015, publisher = , address = , volume = , number = , series = , edition = , month = "
        int count = print.length() - print.replace(pattern, "").length();
        count /= pattern.length()
        count.shouldBe(1)
    }
}