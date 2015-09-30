import com.miniprojekti.misc.*

description 'testi user story'

scenario 'test', {
     given 'testiluokka löytyy', {
         t = new Testiluokka()
     }
     when 'ja kutsutaan metodia testiMetodi', {
         a = t.testiMetodi()
     }
     then 'palautettavan arvon tulisi olla 1', {
         a.shouldBe 1
     }
 }