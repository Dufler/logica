/*
 * Created by SharpDevelop.
 * User: Damiano
 * Date: 21/04/2017
 * Time: 12:53
 * 
 * To change this template use Tools | Options | Coding | Edit Standard Headers.
 */
using System;
using System.Diagnostics;
using System.IO;
using System.Collections;
using System.Data.SQLite;

namespace LogicaLauncher
{
	/// <summary>
	/// Class with program entry point.
	/// </summary>
	internal sealed class Program
	{
		// Holds our connection with the database
        static SQLiteConnection m_dbConnection;
        static ArrayList pluginEliminati;
        static string pluginFolder;
        static string executable;
        
		/// <summary>
		/// Program entry point.
		/// </summary>
		[STAThread]
		private static void Main(string[] args)
		{
			//Console.WriteLine("Launching Logica!");
			//Controllo se il processo principale è già attivo
			string processName = "LogicaMain";
			Process[] pname = Process.GetProcessesByName(processName);
			while (pname.Length > 0)
			{
				System.Threading.Thread.Sleep(500);
				pname = Process.GetProcessesByName(processName);
			}
			//Recupero la cartella di lavoro attuale
			string path = Directory.GetCurrentDirectory();
			//Inizializzo le variabili di appoggio
			pluginEliminati = new ArrayList();
			executable = path + "\\" + processName + ".exe";
			pluginFolder = path + "\\plugins\\";
			//Controllo la cartella del DB
			System.IO.Directory.CreateDirectory(path + "\\db");
			//Connessione al DB
			connectToDatabase();
			//Controllo sui dati
			checkTable();
			//Cancello i plugin doppi
			getFilesToDelete();
			updateDeletedFiles();
			//Avvio Logica
			launchLogica();
		}
		
		// Creates a connection with our database file.
        static void connectToDatabase()
        {
            m_dbConnection = new SQLiteConnection("Data Source=db\\logica.db;Version=3;");
            m_dbConnection.Open();
        }
        
        static void checkTable()
        {
            string sql = "create table if not exists resource_to_delete ( name text PRIMARY KEY );";
            SQLiteCommand command = new SQLiteCommand(sql, m_dbConnection);
            command.ExecuteNonQuery();
        }
        
        static void getFilesToDelete()
        {
            string sql = "select * from resource_to_delete";
            SQLiteCommand command = new SQLiteCommand(sql, m_dbConnection);
            SQLiteDataReader reader = command.ExecuteReader();
            while (reader.Read())
            	deleteFile(reader["name"].ToString());
        }
        
        static void deleteFile(string name)
        {
        	try
			{
        		string file = pluginFolder + name;
        		//Console.WriteLine("Deleting: " + file);
        		File.Delete(file);
        		pluginEliminati.Add(name);
        	} catch(Exception e) {
        		Console.Write(e);
        	}
        }
        
        static void updateDeletedFiles()
        {
        	foreach(string name in pluginEliminati)
        	{
        		string sql = "delete from resource_to_delete where name = '" + name + "'";
            	SQLiteCommand command = new SQLiteCommand(sql, m_dbConnection);
            	command.ExecuteNonQuery();
        	}
        }
        
        static void launchLogica()
        {
        	System.Diagnostics.Process.Start(@executable);
        }
		
	}
}
