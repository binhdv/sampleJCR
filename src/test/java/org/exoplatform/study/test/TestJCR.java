
package org.exoplatform.study.test;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import junit.framework.TestCase;
import org.exoplatform.container.StandaloneContainer;
import org.exoplatform.services.jcr.RepositoryService;
import org.exoplatform.services.jcr.ext.app.SessionProviderService;
import org.exoplatform.services.jcr.ext.common.SessionProvider;

public class TestJCR extends TestCase {
  

  
  protected static StandaloneContainer  container;
  
  static {
    initContainer();
  }
  
 

  private static void initContainer() {
    try {
      String containerConf = Thread.currentThread()
                                   .getContextClassLoader()
                                   .getResource("conf/standalone/configuration.xml")
                                   .toString();
      StandaloneContainer.addConfigurationURL(containerConf);
      container = StandaloneContainer.getInstance();
      
    } catch (Exception e) {
      throw new RuntimeException("Failed to initialize standalone container: " + e.getMessage(), e);
    }
  }
  
  public void testAddTeamMember() throws RepositoryException{
	    
	  RepositoryService repositoryService = 
			  (RepositoryService) container.getComponentInstanceOfType(RepositoryService.class);
      SessionProviderService sessionProviderService = 
    		  (SessionProviderService) container.getComponentInstanceOfType(SessionProviderService.class);
      SessionProvider sessionProvider = 
    		  sessionProviderService.getSystemSessionProvider(null);
      Session session = 
    		  sessionProvider.getSession("binhdv_ws", repositoryService.getCurrentRepository());
      Node root = session.getRootNode();
      Node team = root.addNode("SOC","lab:team");
      team.setProperty("lab:teamName", "SOCIAL");
      Node binh = team.addNode("binh", "lab:member");
      binh.setProperty("lab:name", "binhdv");
      binh.setProperty("lab:age", 29);
      binh.setProperty("lab:team", team.getName());
      session.save();
      System.out.println("=============="+team.getName()+"/"+binh.getPath()+"/"+binh.getProperty("lab:age").getLong());
      assertNotNull(team);
      }
}
