package br.gestor_tarefas_esig.model.Conveter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.gestor_tarefas_esig.model.DAO.PrioridadeDAO;
import br.gestor_tarefas_esig.model.Entity.Prioridade;

@FacesConverter(value="prioridadeConverter", forClass= Prioridade.class)
public class PrioridadeConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value)
			throws ConverterException {
		if (value == null || value.equals("")) {
			System.out.println("Valor nulo no conversor de Prioridade");
		}else {
			Prioridade prioridade = new Prioridade();
			try {
				PrioridadeDAO prioridadeDAO = new PrioridadeDAO();
				prioridade = prioridadeDAO.getById(Integer.parseInt(value));
				return prioridade;
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
		
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object object)
			throws ConverterException {
		try {
			if (object == null) {
				System.out.println("Valor nulo no conversor de Prioridade");
			}else {
				return  String.valueOf( ((Prioridade) object).getId());
			}
		} catch (ConverterException e) {
			e.printStackTrace();
		}
		return null;

	}

}
