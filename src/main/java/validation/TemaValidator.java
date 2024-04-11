package validation;
import domain.Tema;

public class TemaValidator implements Validator<Tema> {
    public void validate(Tema tema) throws ValidationException {
        if (tema.getID() == null || tema.getID().equals("")) {
            throw new ValidationException("ID invalid! \n");
        }
        if (tema.getDescriere() == null || tema.getDescriere().equals("")) {
            throw new ValidationException("Descriere invalida! \n");
        }
        // the final condition in the following if statement used to be tema.getDeadline() < tema.getStartline()
        // this would cause a bug when adding a valid assignment
        if (tema.getDeadline() < 1 || tema.getDeadline() > 14 || tema.getDeadline() > tema.getStartline()) {
            throw new ValidationException("Deadline invalid! \n");
        }
        // the final condition in the following if statement used to be tema.getStartline() > tema.getDeadline()
        // this would cause a bug when adding a valid assignment
        if (tema.getStartline() < 1 || tema.getStartline() > 14 || tema.getStartline() < tema.getDeadline()) {
            throw new ValidationException("Data de primire invalida! \n");
        }
    }
}

