package pt.training.maven.jee;

import org.testng.annotations.Test;

public class HelloWorldTest
{
   @Test
   public void testGetText() {
      HelloWorld fixture = new HelloWorld();
      assert "Hello World!".equals(fixture.getText());
   }
}
