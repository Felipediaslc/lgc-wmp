package com.luisgomezcaballero.weeklymenuplanner.configuration;

import java.util.Locale;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * Spring Web Configuration class.
 * 
 * @author Luis
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan
public class SpringWebConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {

  private static final String LOCALE_EN = "en";
  /**
   * Context of the application.
   */
  private ApplicationContext context;

  /**
   * Constructor for the configuration class.
   * 
   * @param context Context.
   */
  public SpringWebConfig(final ApplicationContext context) {
    super();
    this.context = context;
  }

  /**
   * Method for internationalization.
   * 
   * @return
   */
  @Bean
  public ResourceBundleMessageSource messageSource() {
    final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    messageSource.setBasename("messages");
    return messageSource;
  }

  /**
   * Resolver for internationalisation.
   * 
   * @return
   */
  @Bean
  public LocaleResolver localeResolver() {
    final SessionLocaleResolver slr = new SessionLocaleResolver();
    slr.setDefaultLocale(new Locale(LOCALE_EN));
    return slr;
  }

  /**
   * Setter for context of the application .
   */
  @Override
  public void setApplicationContext(final ApplicationContext context) {
    this.context = context;
  }

  /**
   * Adds formatters to the registry.
   */
  @Override
  public void addFormatters(final FormatterRegistry registry) {
    super.addFormatters(registry);
    registry.addFormatter(dateFormatter());
  }

  /**
   * Gets a date formatter.
   * 
   * @return
   */
  @Bean
  public DateFormatter dateFormatter() {
    return new DateFormatter();
  }
}