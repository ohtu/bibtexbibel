import com.miniprojekti.bibtexbible.logic.*;
import com.miniprojekti.bibtexbible.io.StubIO;
import com.miniprojekti.bibtexbible.io.IO;
import com.miniprojekti.bibtexbible.ui.ConsoleUI;
import com.miniprojekti.bibtexbible.ui.UI;

description 'User can delete an added reference'

scenario "user can delete reference", {
    given 'application is initiliazed', {
        io = new StubIO("1", "1", "Kirjoittaja", "", "Titteli", "2015", "", "", "", "", "", "", "",
                        "3", "1", "2", "0")
        
        rc = new ReferenceController()        
        ui = new ConsoleUI(io,rc)
        ui.run();
    }
    when 'user adds a reference then deletes it', {
        prints = io.getPrints()
    }
    then 'prints should contain "No references" implicating succesfull deletion', {
        prints.toString().contains("No references.").shouldBe(true)
    }
}

scenario "user can't delete when there's no references", {
    given 'application is initiliazed', {
        io = new StubIO("3", "0")
        
        rc = new ReferenceController()        
        ui = new ConsoleUI(io,rc)
        ui.run();
    }
    when 'correct commands have been printed', {
        prints = io.getPrints()
    }
    then 'prints should contain "No references in the list." implicating that theres nothing to delete', {
        prints.toString().contains("No references in the list.").shouldBe(true)
    }
}