/**
 * Package containing security related classes. For the most part, you will not need to look at these classes during the workshop,
 * unless you're interested in how the security is implemented.
 *
 * <p>The security configuration enables basic authentication for the application. The user credentials are stored in the database
 * and are managed through the {@link com.ces.slc.workshop.security.application.CustomUserDetailsService} class. The user repository
 * is defined in the {@link com.ces.slc.workshop.security.application.UserRepository} class. By default, passwords are encoded using
 * the BCrypt algorithm.
 *
 * <p>Useful to know is that you can resolve {@link com.ces.slc.workshop.security.domain.User} objects as method arguments in your
 * controllers, as the {@link com.ces.slc.workshop.security.application.UserArgumentResolver} class is registered as a handler method
 * argument resolver.
 */
package com.ces.slc.workshop.security;
