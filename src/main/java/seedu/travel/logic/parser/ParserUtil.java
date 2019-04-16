package seedu.travel.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.travel.commons.core.index.Index;
import seedu.travel.commons.util.StringUtil;
import seedu.travel.logic.parser.exceptions.ParseException;
import seedu.travel.model.place.Address;
import seedu.travel.model.place.CountryCode;
import seedu.travel.model.place.DateVisited;
import seedu.travel.model.place.Description;
import seedu.travel.model.place.Name;
import seedu.travel.model.place.Photo;
import seedu.travel.model.place.Rating;
import seedu.travel.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String EMPTY_PHOTO_PATH = "pBSgcMnA";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String countryCode} into a {@code CountryCode}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code countryCode} is invalid.
     */
    public static CountryCode parseCountryCode(String countryCode) throws ParseException {
        requireNonNull(countryCode);
        String trimmedCountryCode = countryCode.trim();
        if (!CountryCode.isValidCountryCode(trimmedCountryCode)) {
            throw new ParseException(CountryCode.MESSAGE_CONSTRAINTS);
        }
        return new CountryCode(trimmedCountryCode);
    }

    /**
     * Parses a {@code String dateVisited} into a {@code DateVisited}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code dateVisited} is invalid.
     */
    public static DateVisited parseDateVisited(String dateVisited) throws ParseException {
        requireNonNull(dateVisited);
        String trimmedDateVisited = dateVisited.trim();
        if (!DateVisited.isCorrectDateFormat(trimmedDateVisited)) {
            throw new ParseException(DateVisited.MESSAGE_INCORRECT_FORMAT);
        }
        if (!DateVisited.doesDateExist(trimmedDateVisited)) {
            throw new ParseException(DateVisited.MESSAGE_DATE_DOES_NOT_EXIST);
        }
        if (!DateVisited.isValidDateVisited(trimmedDateVisited)) {
            throw new ParseException(DateVisited.MESSAGE_FUTURE_DATE_ADDED);
        }
        return new DateVisited(trimmedDateVisited);
    }

    /**
     * Parses a {@code String rating} into a {@code Rating}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code rating} is invalid.
     */
    public static Rating parseRating(String rating) throws ParseException {
        requireNonNull(rating);
        String trimmedRating = rating.trim();
        if (!Rating.isValidRating(trimmedRating)) {
            throw new ParseException(Rating.MESSAGE_CONSTRAINTS);
        }
        return new Rating(trimmedRating);
    }

    /**
     * Parses a {@code String travel} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code travel} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String description} into an {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static Description parseDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        if (!Description.isValidDescription(trimmedDescription)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(trimmedDescription);
    }

    /**
     * Parses a {@code String filepath} into an {@code Photo}.
     * A single leading and trailing " will be trimmed.
     *
     * @throws ParseException if the given {@code String filepath} is invalid.
     */
    public static Photo parsePhoto(String filepath) throws ParseException {
        requireNonNull(filepath);
        String trimmedFilepath = filepath.trim();

        if (trimmedFilepath.equals("")) {
            throw new ParseException(Photo.MESSAGE_CONSTRAINTS);
        }

        if (trimmedFilepath.substring(0, 1).equals("\"")) {
            trimmedFilepath = trimmedFilepath.substring(1, filepath.length() - 1);
        }

        if (!Photo.isValidPhotoFilepath(trimmedFilepath)) {

            if (trimmedFilepath.equals(EMPTY_PHOTO_PATH)) {
                return new Photo(EMPTY_PHOTO_PATH);
            } else {
                throw new ParseException(Photo.MESSAGE_CONSTRAINTS);
            }
        }
        return new Photo(trimmedFilepath);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }
}
