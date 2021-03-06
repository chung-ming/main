package seedu.travel.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.travel.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.travel.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.travel.logic.parser.CliSyntax.PREFIX_COUNTRY_CODE;
import static seedu.travel.logic.parser.CliSyntax.PREFIX_DATE_VISITED;
import static seedu.travel.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.travel.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.travel.logic.parser.CliSyntax.PREFIX_PHOTO;
import static seedu.travel.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.travel.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.travel.commons.core.index.Index;
import seedu.travel.logic.commands.EditCommand;
import seedu.travel.logic.commands.EditCommand.EditPlaceDescriptor;
import seedu.travel.logic.parser.exceptions.ParseException;
import seedu.travel.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_COUNTRY_CODE,
            PREFIX_DATE_VISITED, PREFIX_RATING, PREFIX_DESCRIPTION, PREFIX_ADDRESS, PREFIX_PHOTO, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditPlaceDescriptor editPlaceDescriptor = new EditPlaceDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPlaceDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_COUNTRY_CODE).isPresent()) {
            editPlaceDescriptor.setCountryCode(ParserUtil
                .parseCountryCode(argMultimap.getValue(PREFIX_COUNTRY_CODE).get()));
        }
        if (argMultimap.getValue(PREFIX_DATE_VISITED).isPresent()) {
            editPlaceDescriptor.setDateVisited(ParserUtil
                .parseDateVisited(argMultimap.getValue(PREFIX_DATE_VISITED).get()));
        }
        if (argMultimap.getValue(PREFIX_RATING).isPresent()) {
            editPlaceDescriptor.setRating(ParserUtil.parseRating(argMultimap.getValue(PREFIX_RATING).get()));
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editPlaceDescriptor.setDescription(ParserUtil.parseDescription(
                    argMultimap.getValue(PREFIX_DESCRIPTION).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editPlaceDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_PHOTO).isPresent()) {
            editPlaceDescriptor.setPhoto(ParserUtil.parsePhoto(argMultimap.getValue(PREFIX_PHOTO).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPlaceDescriptor::setTags);

        if (!editPlaceDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editPlaceDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}
