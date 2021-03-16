SELECT mantis_bug_table.SUMMARY
		,mantis_bug_text_table.description
		,mantis_bug_text_table.steps_to_reproduce
		,mantis_bug_text_table.additional_information
		,mantis_project_table.name
		,CASE mantis_bug_table.CATEGORY_ID WHEN 1 THEN 'General' END AS CATEGORY
		,mantis_bug_table.id
  FROM mantis_bug_table
 INNER JOIN mantis_bug_text_table ON mantis_bug_table.bug_text_id = mantis_bug_text_table.id
 INNER JOIN mantis_project_table ON mantis_bug_table.project_id = mantis_project_table.id
WHERE mantis_project_table.name = '$nomeProjeto'