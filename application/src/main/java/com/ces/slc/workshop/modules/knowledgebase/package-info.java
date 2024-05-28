/**
 * This package contains the classes that are responsible for the knowledgebase functionality of the application. A knowledgebase
 * is a repository of documents that contain information about a particular subject. The knowledgebase module allows users to
 * define these documents and organize their contents into a {@link com.ces.slc.workshop.modules.knowledgebase.domain.KnowledgebaseLevel
 * level-based hierarchy}. The documents can be referenced by other modules in the application, such as the {@link
 * com.ces.slc.workshop.modules.estimating} module.
 *
 * <p>Hint, the knowledgebase module has a wrongly coupled dependency on the {@link com.ces.slc.workshop.modules.estimating} module to
 * resolve references to knowledgebase documents in estimates. This dependency should be removed to make the knowledgebase module
 * independent of the estimating module.
 */
package com.ces.slc.workshop.modules.knowledgebase;
