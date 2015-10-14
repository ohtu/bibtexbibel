import org.junit.contrib.java.lang.system.SystemOutRule;
import com.miniprojekti.bibtexbible.*;
import com.miniprojekti.bibtexbible.io.StubIO;
import com.miniprojekti.bibtexbible.io.IO;
import com.miniprojekti.bibtexbible.ui.ConsoleUI;
import com.miniprojekti.bibtexbible.ui.UI;

description 'User can see added references listed'

scenario "user can list references", {
    given 'application is initiliazed correctly', {
        io = new StubIO("1", "1", "nimi", "1", "osoite", "kuukausi", "2015", "kirjoittaja", "sarja", "julkaisija", "editio", "otsikko", "2", "0")
        ui = new ConsoleUI(io)
        rc = new ReferenceController(ui)
        app = new App(ui, rc)
        app.runConsoleApp()
    }
    when 'correct commands are entered', {
        prints = io.getPrints()
    }
    then 'user can see reference listed', {
        prints.toString().contains(" volume = nimi, number = 1, address = osoite, " + 
            "month = kuukausi, year = 2015, author = kirjoittaja, series = sarja, " +
            "publisher = julkaisija, edition = editio, title = otsikko").shouldBe(true)
    }
}

scenario "user can't list references when there is none", {
    given 'application is initiliazed correctly', {
        io = new StubIO("2", "0")
        ui = new ConsoleUI(io)
        rc = new ReferenceController(ui)
        app = new App(ui, rc)
        app.runConsoleApp()
    }
    when 'correct commands are entered', {
        prints = io.getPrints()
    }
    then 'user can see see only ONE reference listed', {
        String print = prints.toString()
        print.contains("No references.").shouldBe(true)
    }
}