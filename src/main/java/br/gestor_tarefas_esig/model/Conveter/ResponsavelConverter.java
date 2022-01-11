package br.gestor_tarefas_esig.model.Conveter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.gestor_tarefas_esig.model.DAO.ResponsavelDAO;
import br.gestor_tarefas_esig.model.Entity.Responsavel;

@FacesConverter(value="responsavelConverter", forClass= Responsavel.class)
public class ResponsavelConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value)
			throws ConverterException {
		if (value == null || value.equals("")) {
			System.out.println("Valor nulo no conversor de Responsavel");
		}
		else {
			
			Responsavel responsavel = new Responsavel();
			
			try {
				ResponsavelDAO responsavelDAO = new ResponsavelDAO();
				responsavel = responsavelDAO.getById(Integer.parseInt(value));
				return responsavel;
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
			if (object == null){ 
				System.out.println("Valor nulo no conversor de Responsavel");
			}else {
				return  String.valueOf( ((Responsavel) object).getId());
			}

		} catch (ConverterException e) {
			e.printStackTrace();
		}
		return null;

	}

}
