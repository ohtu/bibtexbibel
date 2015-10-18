import org.junit.contrib.java.lang.system.SystemOutRule;
import com.miniprojekti.bibtexbible.*;
import com.miniprojekti.bibtexbible.io.StubIO;
import com.miniprojekti.bibtexbible.io.IO;
import com.miniprojekti.bibtexbible.ui.ConsoleUI;
import com.miniprojekti.bibtexbible.ui.UI;

description 'User can delete an added reference'

scenario "user can delete reference", {
    given 'application is initiliazed', {
        io = new StubIO("1", "1", "nimi", "1", "osoite", "kuukausi", "2015", "kirjoittaja", "sarja", "julkaisija", "editio", "otsikko", "3", "1", "2", "0")
        ui = new ConsoleUI(io)
        rc = new ReferenceController(ui)
        app = new App(ui, rc)
        app.runConsoleApp()
    }
    when 'correct commands have been printed', {
        prints = io.getPrints()
    }
    then 'prints should contain "No references" implicating succesfull deletion', {
        prints.toString().contains(" volume = nimi, number = 1, address = osoite, " + 
            "month = kuukausi, year = 2015, author = kirjoittaja, series = sarja, " +
            "publisher = julkaisija, edition = editio, title = otsikko").shouldBe(true)
        
        prints.toString().contains("No references.").shouldBe(true)
    }
}

scenario "user can't delete when there's no references", {
    given 'application is initiliazed', {
        io = new StubIO("3", "0")
        ui = new ConsoleUI(io)
        rc = new ReferenceController(ui)
        app = new App(ui, rc)
        app.runConsoleApp()
    }
    when 'correct commands have been printed', {
        prints = io.getPrints()
    }
    then 'prints should contain "No references in the list." implicating that theres nothing to delete', {
        prints.toString().contains("No references in the list.").shouldBe(true)
    }
}