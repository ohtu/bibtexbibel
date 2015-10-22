import com.miniprojekti.bibtexbible.logic.*;
import com.miniprojekti.bibtexbible.io.StubIO;
import com.miniprojekti.bibtexbible.io.IO;
import com.miniprojekti.bibtexbible.ui.ConsoleUI;
import com.miniprojekti.bibtexbible.ui.UI;
import java.io.*;

description 'User can import references in BibTex format'

scenario "user can import BibTex file", {
    given 'application is initiliazed correctly', {
        io = new StubIO("5", "demodb.bib", "0")
        
        rc = new ReferenceController()        
        ui = new ConsoleUI(io,rc)
        ui.run();
    }
    when 'correct commands are entered', {
        prints = io.getPrints()
    }
    then 'user sees success message and data got imported', {
        rc.list().size().shouldBe(7)
        String print = prints.toString()
        print.contains("Much import very success wow!").shouldBe(true)
    }
}

scenario "user can't import an empty file", {
    given 'application is initiliazed correctly', {
        io = new StubIO("5", "testi", "0")
        
        rc = new ReferenceController()        
        ui = new ConsoleUI(io,rc)
        ui.run();
    }
    when 'correct commands are entered', {
        prints = io.getPrints()
    }
    then 'user gets correct error message and no imported data exists', {
        rc.list().size().shouldBe(0)
        String print = prints.toString()
        print.contains("Importing from file was unsuccessful. Clearing database...").shouldBe(true)
    }
}
