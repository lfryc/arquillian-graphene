package org.jboss.arquillian.graphene.page;

import java.util.List;

import org.jboss.arquillian.test.spi.TestClass;

/**
 * Returns the list of {@link PageExtension} known by the given module.
 * 
 * Those extensions will be registered in the system, so they can be loaded from external locations if necessary.
 * 
 * @author Lukas Fryc
 */
public interface PageExtensionProvider {

    List<PageExtension> getPageExtensions(TestClass testClass);
}
