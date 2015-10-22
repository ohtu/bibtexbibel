import org.junit.contrib.java.lang.system.SystemOutRule;
import com.miniprojekti.bibtexbible.logic.*;
import com.miniprojekti.bibtexbible.io.StubIO;
import com.miniprojekti.bibtexbible.io.IO;
import com.miniprojekti.bibtexbible.ui.ConsoleUI;
import com.miniprojekti.bibtexbible.ui.UI;
import java.io.*;

description 'User can export his references in BibTex format'

scenario "user can export BibTex", {
    given 'application is initiliazed correctly', {
        io = new StubIO("1", "1", "Kirjoittaja", "", "Titteli Äkkösillä", "2015", "", "", "", "", "", "", "",
                        "4", "testi", "0")
        
        rc = new ReferenceController()        
        ui = new ConsoleUI(io,rc)
        ui.run();
    }
    when 'correct commands are entered', {
        prints = io.getPrints()
    }
    then 'user can open open BibTex file', {
        File file = new File("testi.bib")
        FileReader reader = new FileReader(file)
        BufferedReader br = new BufferedReader(reader)
        StringBuilder sb = new StringBuilder()
        String line = br.readLine()
        line.equals("@Book{Kirj2015, ").shouldBe true
        line = br.readLine()
        line.equals("author = \"Kirjoittaja\", ").shouldBe true
        line = br.readLine()
        line.equals("title = \"Titteli {\\\"A}kk{\\\"o}sill{\\\"a}\", ").shouldBe true
        line = br.readLine()
        line.equals("year = \"2015\", ").shouldBe true
        line = br.readLine()
        line.equals("}").shouldBe true
        file.delete();
    }
}
