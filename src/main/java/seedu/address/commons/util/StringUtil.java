package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Helper functions for handling strings.
 */
public class StringUtil {

    /**
     * Returns true if the {@code sentence} contains the {@code word}.
     *   Ignores case, but a full word match is required.
     *   <br>examples:<pre>
     *       containsWordIgnoreCase("ABc def", "abc") == true
     *       containsWordIgnoreCase("ABc def", "DEF") == true
     *       containsWordIgnoreCase("ABc def", "AB") == false //not a full word match
     *       </pre>
     * @param sentence cannot be null
     * @param word cannot be null, cannot be empty, must be a single word
     */
    public static boolean containsWordIgnoreCase(String sentence, String word) {
        requireNonNull(sentence);
        requireNonNull(word);

        String preppedWord = word.trim();
        checkArgument(!preppedWord.isEmpty(), "Word parameter cannot be empty");
        checkArgument(preppedWord.split("\\s+").length == 1,
                "Word parameter should be a single word");

        String preppedSentence = sentence;
        String[] wordsInPreppedSentence = preppedSentence.split("\\s+");

        return Arrays.stream(wordsInPreppedSentence)
                .anyMatch(preppedWord::equalsIgnoreCase);
    }

    /**
     * Returns true if the {@code tagsList} contains the {@code tag}.
     *   Ignores case, but a full word match is required.
     *   <br>examples:<pre>
     *       containsTagIgnoreCase("ABc def", "abc") == true
     *       containsTagIgnoreCase("ABc def", "DEF") == true
     *       containsTagIgnoreCase("ABc def", "AB") == false //not a full word match
     *       </pre>
     * @param tagsList cannot be null
     * @param tag cannot be null, cannot be empty, must be a single word
     */
    public static boolean containsTagIgnoreCase(Set<Tag> tagsList, String tag) {
        requireNonNull(tagsList);
        requireNonNull(tag);

        String preppedTag = tag.trim();
        checkArgument(!preppedTag.isEmpty(), "Tag parameter cannot be empty");
        checkArgument(preppedTag.split("\\s+").length == 1,
                "Tag parameter should be a single word");

        Tag[] preppedTagsList = tagsList.toArray(new Tag[0]);

        return Arrays.stream(preppedTagsList).map(t -> t.tagName)
                .anyMatch(preppedTag::equalsIgnoreCase);
    }

    /**
     * Returns true if the {@code ratingsList} contains the {@code rating}.
     *   A valid rating match from 1 to 5 is required.
     *   <br>examples:<pre>
     *       containsRating("1 4", "1") == true
     *       containsRating("1 4", "4") == true
     *       containsRating("1 4", "5") == false //not a valid match
     *       </pre>
     * @param ratingsList cannot be null
     * @param rating cannot be null, cannot be empty, must be a single word
     */
    public static boolean containsRating(String ratingsList, String rating) {
        requireNonNull(ratingsList);
        requireNonNull(rating);

        String preppedRating = rating.trim();
        checkArgument(!preppedRating.isEmpty(), "Rating parameter cannot be empty");
        checkArgument(preppedRating.split("\\s+").length == 1,
                "Rating parameter should be a single value from 1 to 5");

        String preppedRatingsList = ratingsList;
        String[] ratingsInPreppedRatingsList = preppedRatingsList.split("\\s+");

        return Arrays.stream(ratingsInPreppedRatingsList)
                .anyMatch(preppedRating::equalsIgnoreCase);
    }

    /**
     * Returns true if the {@code countryCodeList} contains the {@code countryCode}.
     *   Ignores case, but a full word match is required.
     *   Correct country code is also required.
     *   <br>examples:<pre>
     *       containsCountryCode("SGp usa", "abc") == true
     *       containsCountryCode("SGp usa", "USA") == true
     *       containsCountryCode("SGp usa", "sg") == false // not a full word match
     *       containsCountryCode("SGp usa", "sga") == false // not a valid code
     *       </pre>
     * @param countryCodeList cannot be null
     * @param countryCode cannot be null, cannot be empty, must be a single word
     */
    public static boolean containsCountryCode(String countryCodeList, String countryCode) {
        requireNonNull(countryCodeList);
        requireNonNull(countryCode);

        String preppedCountryCode = countryCode.trim();
        checkArgument(!preppedCountryCode.isEmpty(), "Country Code parameter cannot be empty");
        checkArgument(preppedCountryCode.split("\\s+").length == 1,
            "Country Code parameter should be a single word");

        String preppedCountryCodeList = countryCodeList;
        String[] countryCodesInPreppedCountryCodeList = preppedCountryCodeList.split("\\s+");

        return Arrays.stream(countryCodesInPreppedCountryCodeList)
            .anyMatch(preppedCountryCode::equalsIgnoreCase);
    }

    /**
     * Returns a detailed message of the t, including the stack trace.
     */
    public static String getDetails(Throwable t) {
        requireNonNull(t);
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return t.getMessage() + "\n" + sw.toString();
    }

    /**
     * Returns true if {@code s} represents a non-zero unsigned integer
     * e.g. 1, 2, 3, ..., {@code Integer.MAX_VALUE} <br>
     * Will return false for any other non-null string input
     * e.g. empty string, "-1", "0", "+1", and " 2 " (untrimmed), "3 0" (contains whitespace), "1 a" (contains letters)
     * @throws NullPointerException if {@code s} is null.
     */
    public static boolean isNonZeroUnsignedInteger(String s) {
        requireNonNull(s);

        try {
            int value = Integer.parseInt(s);
            return value > 0 && !s.startsWith("+"); // "+1" is successfully parsed by Integer#parseInt(String)
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
