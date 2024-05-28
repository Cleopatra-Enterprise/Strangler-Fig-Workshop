/**
 * This package contains the classes that are responsible for estimating the cost of a project. Estimating is the process of
 * calculating the cost of a project based on the materials, labor, and other resources required to complete the project. All
 * of these resources are represented by {@link com.ces.slc.workshop.modules.estimating.domain.EstimateComponent} objects,
 * which are organized into a hierarchy to represent the structure of the project.
 *
 * <p>The estimating module has a strict dependency on the {@link com.ces.slc.workshop.modules.knowledgebase} module, as it
 * allows users to reference knowledgebase documents in their estimates. This dependency may or may not be desired, depending
 * on how you want to structure the application. If you want to make the estimating module independent of the knowledgebase
 * module, you will need to remove this dependency and find another way to resolve knowledgebase references in estimates.
 */
package com.ces.slc.workshop.modules.estimating;
