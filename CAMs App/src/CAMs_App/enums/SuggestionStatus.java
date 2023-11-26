package CAMs_App.enums;

import CAMs_App.entity.Suggestions;

/**
 * The {@link SuggestionsStatus} enum represents the suggestions status in the system.
 * The {@link Suggestions} uses the following enumerations of {@link SuggestionsStatus}.
 * 
 *  @author Denis Yu
 *  @version 1.0
 *  @since 2023-10-25
 */
public enum SuggestionStatus {
    /**
     * Represents a new {@link Suggestions}.
     */
    NEW,
    /**
     * Represents a {@link Suggestions} that is processing.
     */
    PROCESSING,
    /**
     * Represents a {@link Suggestions} that has been processed.
     */
    PROCESSED
}
