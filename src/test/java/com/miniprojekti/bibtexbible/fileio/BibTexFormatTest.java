/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miniprojekti.bibtexbible.fileio;

import com.miniprojekti.bibtexbible.domain.Article;
import com.miniprojekti.bibtexbible.domain.Book;
import com.miniprojekti.bibtexbible.domain.InProceedings;
import com.miniprojekti.bibtexbible.domain.Reference;
import com.miniprojekti.bibtexbible.domain.ReferenceList;
import static com.miniprojekti.misc.Tool.replaceNullsWithEmpty;
import java.io.File;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author asjuvone
 */
public class BibTexFormatTest {
    
    static File file;
    static Writer writer;
    static ReferenceList references;
    
    public BibTexFormatTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws IOException {
        file = new File("pdf-kaantaja/sigproc.bib");
        writer = new Writer(file);
    }
    
    @AfterClass
    public static void tearDownClass() throws IOException {
        writer.close();
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void writeSigProcFileInBibTex() throws IOException {
        references = new ReferenceList();
        
        Book book1 = new Book();
        book1.setID("SWEBOK");
        book1.setProperty("publisher", "IEEE Computer SocietyäÄöÖåÅ");
        book1.setProperty("editor", "Abram, Aman and More, James W. and Bourgue Pierre and Dupuis Robert");
        book1.setProperty("year", "2004");
        book1.setProperty("title", "Guide to the Software Engineering Body of Knownledge");
        replaceNullsWithEmpty(book1);
        
        Book book2 = new Book();
        book2.setID("BA04");
        book2.setProperty("author", "Beck, Kent and Andres, Cynthia");
        book2.setProperty("title", "Extreme Programming Explained: Embrace Change (2nd Edition)");
        book2.setProperty("year", "2004");
        book2.setProperty("publisher", "Addison-Wesley Professional");
        replaceNullsWithEmpty(book2);
        
        Book book3 = new Book();
        book3.setID("Martin09");
        book3.setProperty("author", "Martin, Robert C");
        book3.setProperty("title", "Clean Code: A Handbook of Agile Software Craftsmanship");
        book3.setProperty("year", "2008");
        book3.setProperty("publisher", "Prentice Hall");
        replaceNullsWithEmpty(book3);
        
        Book book4 = new Book();
        book4.setID("scrum");
        book4.setProperty("author", "Ken Schwaber and Mike Beedle");
        book4.setProperty("title", "Agile Software Development with SCRUM");
        book4.setProperty("year", "2002");
        book4.setProperty("publisher", "Prentice Hall");
        replaceNullsWithEmpty(book4);
        
        Article article = new Article();
        article.setID("fox");
        article.setProperty("author", "Fox, Armando and Patterson, David");
        article.setProperty("title", "Crossing the software education chasm");
        article.setProperty("journal", "Communications of ACM");
        article.setProperty("volume", "55");
        article.setProperty("number", "5");
        article.setProperty("month", "may");
        article.setProperty("year", "2012");
        article.setProperty("pages", "44--49");
        article.setProperty("publisher", "ACM");
        article.setProperty("address", "New york, NY, USA");
        replaceNullsWithEmpty(article);
        
        InProceedings inproceedings1 = new InProceedings();
        inproceedings1.setID("Begel_2008");
        inproceedings1.setProperty("author", "Begel, Andrew and Simon, Beth");
        inproceedings1.setProperty("title", "Struggles of new college graduates in their first software development job");
        inproceedings1.setProperty("booktitle", "Proceedings of the SIGCSE '08");
        inproceedings1.setProperty("year", "2008");
        inproceedings1.setProperty("publisher", "ACM");
        replaceNullsWithEmpty(inproceedings1);
        
        InProceedings inproceedings2 = new InProceedings();
        inproceedings2.setID("royce70");
        inproceedings2.setProperty("author", "Royce, Walker");
        inproceedings2.setProperty("title", "Managing the Development of Large Software Systems");
        inproceedings2.setProperty("booktitle", "Proceedings of IEEE WESCON 26");
        inproceedings2.setProperty("organization", "TeX Users Group");
        inproceedings2.setProperty("month", "August");
        inproceedings2.setProperty("year", "1970");
        replaceNullsWithEmpty(inproceedings2);

        references.add(book1);
        references.add(book2);
        references.add(book3);
        references.add(book4);
        references.add(inproceedings1);
        references.add(inproceedings2);
        references.add(article);

        for (Reference reference : references.list()) {
            writer.write(reference.toBibTex());
        }
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
