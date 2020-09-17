package br.fatec.financas.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.fatec.financas.model.Conta;
import br.fatec.financas.service.ContaService;

@Path("/contas")
public class ContaResource {
	private ContaService service = new ContaService();

	@GET
	@Produces("application/json")
	public Response getAll() {
		return Response.ok(service.findAll()).build();
	}

	@Path("/{numero}")
	@GET
	@Produces("application/json")
	public Response get(@PathParam("numero") Long numero) {
		Conta _conta = service.find(numero);
		if (_conta != null)
			return Response.ok(_conta).build();
		return Response.status(Response.Status.NOT_FOUND).build();
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public Response add(Conta conta) {
		service.add(conta);
		return Response.ok(conta).build();
	}

	@PUT
	@Produces("application/json")
	@Consumes("application/json")
	public Response update(Conta conta) {
		if (service.update(conta)) {
			return Response.ok(conta).build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}

	@Path("/{numero}")
	@DELETE
	@Produces("application/json")
	public Response delete(@PathParam("numero") Long numero) {
		if (service.delete(numero)) {
			return Response.ok().build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
}
