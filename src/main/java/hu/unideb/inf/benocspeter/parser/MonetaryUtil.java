package hu.unideb.inf.benocspeter.parser;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;

public class MonetaryUtil {

    private static final HashMap<String, Locale> currencyToLocale = new HashMap<String, Locale>();
    private static final HashMap<String, String> bdCurrencySymbols = new HashMap<String, String>();

    static {
        final Object[][] temp = new Object[][] {
                {"AUD", new Locale("en", "AU"), "A$"},
                {"EUR", Locale.FRANCE, null},
                {"USD", Locale.US, "US$"},
                {"GBP", Locale.UK, null},
                {"NZD", new Locale("en", "NZ"), "NZ$"},
                {"SGD", new Locale("en", "SG"), "S$"},
                {"HKD", new Locale("zh", "HK"), null},
                {"CAD", Locale.CANADA, "C$"},
                {"CZK", new Locale("cs", "CZ"), null},
                {"ILS", new Locale("iw", "IL"), null},	// TODO
                {"JPY", Locale.JAPAN, null},
                {"NOK", new Locale("no", "NO"), "NOK"},
                {"PLN", new Locale("pl", "PL"), null},
                {"SEK", new Locale("sv", "SE"), "SEK"},	// TODO
                {"CHF", new Locale("de", "CH"), null},
                {"THB", new Locale("th", "TH"), null},
                {"DKK", new Locale("da", "DK"), "DKK"},
                {"HUF", new Locale("hu", "HU"), "Ft"},
                {"TWD", Locale.TAIWAN, null},
                {"ZAR", new Locale("en", "ZA"), null},
                {"MXN", new Locale("es", "MX"), "Mex$"},
                {"ARS", new Locale("es", "AR"), "ARS$"},
                {"CLP", new Locale("es", "CL"), "CLP$"},
                {"MYR", new Locale("ms", "MY"), null},
                {"KRW", new Locale("ko", "KR"), null},
                {"IDR", new Locale("in", "ID"), null}
        };
        for (Object[] o : temp) {
            currencyToLocale.put((String) o[0], (Locale) o[1]);
            if (o[2] != null) {
                bdCurrencySymbols.put((String) o[0], (String) o[2]);
            }
        }
    }

    public static DecimalFormat createDecimalFormat(String currencyCode) {
        DecimalFormat format = (DecimalFormat) NumberFormat.getCurrencyInstance(currencyToLocale.get(currencyCode));
        DecimalFormatSymbols dfs = format.getDecimalFormatSymbols();
        if (bdCurrencySymbols.containsKey(currencyCode)) {
            dfs.setCurrencySymbol(bdCurrencySymbols.get(currencyCode));
            format.setDecimalFormatSymbols(dfs);
        }
        return format;
    }
}