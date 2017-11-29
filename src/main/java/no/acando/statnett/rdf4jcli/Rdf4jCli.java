package no.acando.statnett.rdf4jcli;

import org.eclipse.rdf4j.common.iteration.Iterations;
import org.eclipse.rdf4j.model.Resource;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.impl.SimpleIRI;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.RepositoryResult;
import org.eclipse.rdf4j.repository.http.HTTPRepository;
import org.eclipse.rdf4j.rio.RDFFormat;

import java.io.File;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class Rdf4jCli
{

    public static void main(String[] args) throws IOException {

        ValueFactory vf = SimpleValueFactory.getInstance();

        int i=0;
        File file = new File(args[i++]);
        String baseUrl = args[i++];

        Resource context = vf.createIRI(args[i++]);

        RDFFormat fmt = RDFFormat.TURTLE;

        RepositoryConnection connection = getConnection();

        connection.add(file, baseUrl, fmt, context);

        connection.close();

    }
    public static void main2( String[] args )
    {

       RepositoryConnection connection = getConnection();



        RepositoryResult<Resource> result = connection.getContextIDs();

        for (Resource res: Iterations.asList(result)) {

            System.out.println(res.toString());
        }
        result.close();





        connection.close();

    }

    public static RepositoryConnection getConnection() {
        String rdf4jServer = "http://h1-a-graphdbu2.statnett.no:8080";
        String repositoryID = "morten-test";
        Repository repo = new HTTPRepository(rdf4jServer, repositoryID);
        repo.initialize();

        RepositoryConnection connection = repo.getConnection();
        return connection;
    }
}
