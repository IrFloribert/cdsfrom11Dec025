package bi.gov.otraco.ct.orientation.core.utils;

public class MapUtils {
    // Messages de succès
    public static String create = "Opération créée avec succès!";
    public static String update = "Opération mise à jour avec succès!";
    public static String delete = "Opération supprimée avec succès!";
    public static String success = "Opération réussie!";
    public static String disable = "Désactivation réussie!";
    public static String enable = "Activation réussie!";

    // Messages d'erreur
    public static String failed = "L'opération a échoué!";
    public static String id = "Identifiant non trouvé.";
    public static String code = "Code non trouvé.";
    public static String name = "Le nom existe déjà.";
    public static String not_found = "La ressource n'existe pas.";

    // Messages de validation
    public static String id_regex = "L'identifiant doit contenir trente-six caractères.";
    public static String code_regex = "Le code doit contenir au moins 6 caractères alphanumériques majuscules.";
    public static String name_regex = "Le nom doit contenir entre 2 et 60 caractères.";
    public static String required_field = "Le champ est obligatoire.";
    public static String invalid_format = "Le format est invalide.";

    // Messages spécifiques aux entités
    public static String type_not_found = "Le type n'existe pas.";
    public static String orientation_not_found = "L'orientation n'existe pas.";
    public static String agency_not_found = "L'agence n'existe pas.";
    public static String user_not_found = "L'utilisateur n'existe pas.";

    // Messages de conflit
    public static String duplicate_code = "Ce code existe déjà.";
    public static String duplicate_name = "Ce nom existe déjà.";
    public static String duplicate_entry = "Cette entrée existe déjà.";

    // Messages de permission
    public static String unauthorized = "Vous n'êtes pas autorisé à effectuer cette action.";
    public static String forbidden = "Accès refusé.";

    // Messages informatifs
    public static String no_data = "Aucune donnée disponible.";
    public static String invalid_request = "Requête invalide.";
    public static String server_error = "Erreur serveur interne.";
}