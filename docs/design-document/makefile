default:
	make compile
	make clean


# Clean auxilliary files from the workspace.
# These are in the .gitignore anyway, so they should be pushed regardless.
#
# NOTE: if compiled throughy VSCODE, some weird files are generated.
# 		They will be gitignored, but you'll have to clean them manually.
#		Or change this yourself......
clean:
	@echo "Cleaning auxilliary latex files..."
	rm *.aux
	rm *.toc
	rm *.log

# Compile to pdf.  Requires texlive package. Not sure if this works on windows
compile:
	@echo "pdflatex design-document.tex"
	pdflatex design-document.tex
