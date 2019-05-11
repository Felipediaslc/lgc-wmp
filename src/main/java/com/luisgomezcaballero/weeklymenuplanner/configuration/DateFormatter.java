package com.luisgomezcaballero.weeklymenuplanner.configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.format.Formatter;

/**
 * Date formatter.
 * 
 * @author Luis
 *
 */
public class DateFormatter implements Formatter<Date> {

  /**
   * Message source with date format by locale.
   */
  @Autowired
  private MessageSource messageSource;

  /**
   * Constructor.
   */
  public DateFormatter() {
    super();
  }

  /**
   * Parse method.
   */
  @Override
  public Date parse(final String text, final Locale locale) throws ParseException {
    final SimpleDateFormat dateFormat = createDateFormat(locale);
    return dateFormat.parse(text);
  }

  /**
   * Print method.
   */
  @Override
  public String print(final Date object, final Locale locale) {
    final SimpleDateFormat dateFormat = createDateFormat(locale);
    return dateFormat.format(object);
  }

  /**
   * Create a Simple Date Format with a locale.
   * 
   * @param locale Locale
   * @return
   */
  private SimpleDateFormat createDateFormat(final Locale locale) {
    final String format = this.messageSource.getMessage("date.format", null, locale);
    final SimpleDateFormat dateFormat = new SimpleDateFormat(format, locale);
    dateFormat.setLenient(false);
    return dateFormat;
  }

}